package org.dedkot.config

import com.typesafe.config.Config

case class ServerConfig(port: Int)

object ServerConfig {
  def apply(config: Config): ServerConfig =
    ServerConfig(
      port = config.getInt("port")
    )
}
