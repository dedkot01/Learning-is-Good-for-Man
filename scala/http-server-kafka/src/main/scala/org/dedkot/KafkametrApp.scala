package org.dedkot

import cats.effect._
import com.typesafe.config.ConfigFactory
import org.dedkot.config.ServerConfig
import org.dedkot.controller.UserService
import org.http4s.blaze.server.BlazeServerBuilder

import scala.concurrent.ExecutionContext.global

object KafkametrApp extends IOApp {
  private val serverConfig = ServerConfig(ConfigFactory.load.getConfig("server"))

  override def run(args: List[String]): IO[ExitCode] = {
    BlazeServerBuilder[IO](global)
      .bindHttp(serverConfig.port)
      .withHttpApp(UserService.userService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
  }
}
