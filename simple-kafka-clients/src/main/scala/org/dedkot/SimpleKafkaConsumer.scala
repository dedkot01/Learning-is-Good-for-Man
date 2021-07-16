package org.dedkot

import org.apache.kafka.clients.consumer.KafkaConsumer

import java.time.Duration
import java.util
import java.util.Properties

object SimpleKafkaConsumer extends App {
  val consumerSettings = new Properties
  consumerSettings.setProperty("bootstrap.servers", "localhost:9092")
  consumerSettings.setProperty("group.id", "test")
  consumerSettings.setProperty("enable.auto.commit", "true")
  consumerSettings.setProperty("auto.commit.interval.ms", "1000")
  consumerSettings.setProperty("key.deserializer", "org.apache.kafka.common.serialization.VoidDeserializer")
  consumerSettings.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

  val consumer = new KafkaConsumer[String, String](consumerSettings)
  consumer.subscribe(util.Arrays.asList("topic"))

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    records.forEach(record => println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}"))
  }
}
