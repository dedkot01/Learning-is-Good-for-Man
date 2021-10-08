package org.dedkot

import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.clients.consumer.{ ConsumerConfig, KafkaConsumer }
import org.dedkot.model.User

import java.time.Duration
import java.util
import java.util.Properties

object SimpleKafkaConsumer extends App {
  val consumerSettings = new Properties
  consumerSettings.setProperty("bootstrap.servers", "localhost:9092")
  consumerSettings.setProperty("group.id", "eva02")
  consumerSettings.setProperty("enable.auto.commit", "true")
  consumerSettings.setProperty("auto.commit.interval.ms", "1000")
  consumerSettings.setProperty("key.deserializer", "org.apache.kafka.common.serialization.VoidDeserializer")
  consumerSettings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new GenericSerde[User]())

  val consumer = new KafkaConsumer[Unit, User](consumerSettings, null, new GenericSerde[User]())
  consumer.subscribe(util.Arrays.asList("eva01"))

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    records.forEach(record => println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}"))
  }
}
