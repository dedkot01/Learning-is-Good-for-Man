package org.dedkot.controller

import cats.effect._
import fs2._
import org.dedkot.kafka.{ Consumer, Producer }
import org.dedkot.model.UserNotDone
import org.http4s._
import org.http4s.blaze.server.BlazeServerBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.websocket._
import org.http4s.websocket.WebSocketFrame
import org.http4s.websocket.WebSocketFrame._

import scala.concurrent.ExecutionContext.global

class WebSocketRoutes[F[_]](implicit F: Async[F]) extends Http4sDsl[F] {
  def routes: HttpRoutes[F] =
    HttpRoutes.of[F] {
      case GET -> Root / "wsecho" =>
        val echoReply: Pipe[F, WebSocketFrame, WebSocketFrame] =
          _.collect {
            case Text(msg, _) =>
              println(s"$msg")
              val data = msg.split(' ')
              val user = UserNotDone(data(0).toInt, data(1))
              println(user)
              Producer.send(user)
              println("be send")
              val result = Consumer.get(user)
              println("be get")
              Text("We good: " + result.toString)
            case _ => Text("Something new")
          }

        WebSocketBuilder[F].build(echoReply)
    }

  def stream: Stream[F, ExitCode] =
    BlazeServerBuilder[F](global)
      .bindHttp(8080)
      .withHttpApp(routes.orNotFound)
      .serve
}

object WebSocketRoutes {
  def apply[F[_]: Async]: WebSocketRoutes[F] =
    new WebSocketRoutes[F]
}
