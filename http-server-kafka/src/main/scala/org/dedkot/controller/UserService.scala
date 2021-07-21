package org.dedkot.controller

import cats.data.Kleisli
import cats.effect.IO
import fs2.Pipe
import org.dedkot.kafka.{ Consumer, Producer }
import org.dedkot.model.UserNotDone
import org.http4s.dsl.io._
import org.http4s.implicits.http4sKleisliResponseSyntaxOptionT
import org.http4s.server.websocket.WebSocketBuilder
import org.http4s.websocket.WebSocketFrame
import org.http4s.websocket.WebSocketFrame.Text
import org.http4s.{ HttpRoutes, Request, Response }

object UserService {
  val userService: Kleisli[IO, Request[IO], Response[IO]] =
    HttpRoutes
      .of[IO] {
        case GET -> Root / "user" / "ws" =>
          val reply: Pipe[IO, WebSocketFrame, WebSocketFrame] =
            _.collect {
              case Text(msg, _) =>
                println(s"Msg from client: $msg")

                val data = msg.split(' ')
                val user = UserNotDone(data(0).toInt, data(1))
                Producer.send(user)

                val result = Consumer.get(user)
                Text(result.toString)
              case _ => Text("Something new")
            }
          WebSocketBuilder[IO].build(reply)
      }
      .orNotFound
}
