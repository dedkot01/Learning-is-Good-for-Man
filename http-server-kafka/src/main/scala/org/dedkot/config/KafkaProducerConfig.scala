package org.dedkot.config

import com.typesafe.config.Config

case class KafkaProducerConfig(bootstrapServer: String, topic: String)

object KafkaProducerConfig {
  def apply(config: Config): KafkaProducerConfig =
    KafkaProducerConfig(
      bootstrapServer = config.getString("bootstrap-server"),
      topic = config.getString("topic")
    )
}
