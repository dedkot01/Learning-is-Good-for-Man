package org.dedkot.config

import com.typesafe.config.Config

case class KafkaConsumerConfig(bootstrapServer: String, groupId: String, topic: String)

object KafkaConsumerConfig {
  def apply(config: Config): KafkaConsumerConfig =
    KafkaConsumerConfig(
      bootstrapServer = config.getString("bootstrap-server"),
      groupId = config.getString("group-id"),
      topic = config.getString("topic")
    )
}
