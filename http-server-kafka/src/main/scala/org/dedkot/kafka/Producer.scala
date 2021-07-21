package org.dedkot.kafka

import com.sksamuel.avro4s.kafka.GenericSerde
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }
import org.dedkot.config.KafkaProducerConfig
import org.dedkot.model.UserNotDone

import java.util.Properties

object Producer {
  private val config           = KafkaProducerConfig(ConfigFactory.load.getConfig("kafka-service.producer"))
  private val producerSettings = new Properties
  producerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer)
  producerSettings.put(
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.VoidSerializer"
  )
  producerSettings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new GenericSerde[UserNotDone]())

  def send(user: UserNotDone): Unit = {
    val producer = new KafkaProducer[Unit, UserNotDone](producerSettings, null, new GenericSerde[UserNotDone]())
    producer.send(new ProducerRecord[Unit, UserNotDone](config.topic, user))
    producer.close()
  }
}
