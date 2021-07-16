package org.dedkot

import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerRecord }

import java.util.Properties

object SimpleKafkaProducer extends App {
  val producerSettings = new Properties
  producerSettings.setProperty("bootstrap.servers", "localhost:9092")
  producerSettings.setProperty("acks", "all")
  producerSettings.setProperty("key.serializer", "org.apache.kafka.common.serialization.VoidSerializer")
  producerSettings.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[Unit, String](producerSettings)

  producer.send(new ProducerRecord[Unit, String]("topic", "from my app"))

  producer.close()
}
