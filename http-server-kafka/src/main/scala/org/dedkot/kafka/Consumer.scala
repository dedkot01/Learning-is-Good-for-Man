package org.dedkot.kafka

import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.clients.consumer.{ ConsumerConfig, KafkaConsumer }
import org.dedkot.model.{ UserDone, UserNotDone }

import java.time.Duration
import java.util
import java.util.Properties

object Consumer {
  def get(user: UserNotDone): UserDone = {
    val consumerSettings = new Properties
    consumerSettings.setProperty("bootstrap.servers", "localhost:9092")
    consumerSettings.setProperty("group.id", "eva00")
    consumerSettings.setProperty("enable.auto.commit", "false")
    consumerSettings.setProperty("key.deserializer", "org.apache.kafka.common.serialization.VoidDeserializer")
    consumerSettings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new GenericSerde[UserDone]())

    val consumer = new KafkaConsumer[Unit, UserDone](consumerSettings, null, new GenericSerde[UserDone]())
    consumer.subscribe(util.Arrays.asList("user2"))

    var result: UserDone = null
    while (result == null) {
      val records = consumer.poll(Duration.ofMillis(100))
      records.forEach(record => {
        println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}")
        if (record.value().id == user.id) result = record.value()
      })
    }
    consumer.commitSync()
    consumer.close()
    result
  }
}
