package org.dedkot

import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }
import org.dedkot.model.User

import java.util.Properties

object SimpleKafkaProducer extends App {
  val producerSettings = new Properties
  producerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
  producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.VoidSerializer")
  producerSettings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new GenericSerde[User]())

  val producer = new KafkaProducer[Unit, User](producerSettings, null, new GenericSerde[User]())

  producer.send(new ProducerRecord[Unit, User]("eva00", User("Naruto", 17)))

  producer.close()
}
