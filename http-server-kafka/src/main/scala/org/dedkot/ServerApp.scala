package org.dedkot

import cats.effect._
import org.dedkot.controller.WebSocketRoutes

object ServerApp extends IOApp {
  override def run(args: List[String]): IO[ExitCode] =
    WebSocketRoutes[IO].stream.compile.drain.as(ExitCode.Success)
}
