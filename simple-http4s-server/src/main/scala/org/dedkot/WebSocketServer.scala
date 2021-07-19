package org.dedkot

import cats.effect._
import cats.effect.std.Queue
import cats.syntax.all._
import fs2._
import org.http4s._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.implicits._
import org.http4s.dsl.Http4sDsl
import org.http4s.server.websocket._
import org.http4s.websocket.WebSocketFrame
import org.http4s.websocket.WebSocketFrame._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.global

object BlazeWebSocketExample extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    BlazeWebSocketExampleApp[IO].stream.compile.drain.as(ExitCode.Success)
}

class BlazeWebSocketExampleApp[F[_]](implicit F: Async[F]) extends Http4sDsl[F] {
  def routes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case GET -> Root / "wsecho" =>
        val echoReply: Pipe[F, WebSocketFrame, WebSocketFrame] =
          _.collect {
            case Text(msg, _) => Text("You sent the server: " + msg)
            case _            => Text("Something new")
          }

        Queue
          .unbounded[F, Option[WebSocketFrame]]
          .flatMap { q =>
            val d: Stream[F, WebSocketFrame]     = Stream.fromQueueNoneTerminated(q).through(echoReply)
            val e: Pipe[F, WebSocketFrame, Unit] = _.enqueueNoneTerminated(q)
            WebSocketBuilder[F].build(d, e)
          }
    }

  def stream: Stream[F, ExitCode] =
    BlazeServerBuilder[F](global)
      .bindHttp(8080)
      .withHttpApp(routes.orNotFound)
      .serve
}

object BlazeWebSocketExampleApp {
  def apply[F[_]: Async]: BlazeWebSocketExampleApp[F] =
    new BlazeWebSocketExampleApp[F]
}
