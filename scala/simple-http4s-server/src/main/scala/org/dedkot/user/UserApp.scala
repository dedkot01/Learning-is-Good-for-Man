package org.dedkot.user

import cats.effect.{ ExitCode, IO, IOApp }
import org.dedkot.user.controller.UserService
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object UserApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](global)
      .withHttpApp(UserService.userService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
