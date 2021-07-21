package org.dedkot.kafka

import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }

import java.util.Properties
import org.dedkot.model.UserNotDone

object Producer {
  def send(user: UserNotDone): Unit = {
    val producerSettings = new Properties
    producerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.VoidSerializer")
    producerSettings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new GenericSerde[UserNotDone]())

    val producer = new KafkaProducer[Unit, UserNotDone](producerSettings, null, new GenericSerde[UserNotDone]())

    producer.send(new ProducerRecord[Unit, UserNotDone]("user1", user))

    producer.close()
  }
}
