package org.dedkot.stub

import com.sksamuel.avro4s.kafka.GenericSerde
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.{ ConsumerConfig, KafkaConsumer }
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }
import org.dedkot.model.{ UserDone, UserNotDone }

import java.time.Duration
import java.util
import java.util.Properties

object StreamletStub extends App {
  def send(user: UserDone): Unit = {
    val producerSettings = new Properties
    producerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    producerSettings.put("key.serializer", "org.apache.kafka.common.serialization.VoidSerializer")
    producerSettings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new GenericSerde[UserDone]())

    val producer = new KafkaProducer[Unit, UserDone](producerSettings, null, new GenericSerde[UserDone]())

    producer.send(new ProducerRecord[Unit, UserDone]("user2", user))

    producer.close()
  }

  val consumerSettings = new Properties
  consumerSettings.setProperty("bootstrap.servers", "localhost:9092")
  consumerSettings.setProperty("group.id", "eva02")
  consumerSettings.setProperty("enable.auto.commit", "true")
  consumerSettings.setProperty("auto.commit.interval.ms", "1000")
  consumerSettings.setProperty("key.deserializer", "org.apache.kafka.common.serialization.VoidDeserializer")
  consumerSettings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new GenericSerde[UserNotDone]())

  val consumer = new KafkaConsumer[Unit, UserNotDone](consumerSettings, null, new GenericSerde[UserNotDone]())
  consumer.subscribe(util.Arrays.asList("user1"))

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    records.forEach(record => {
      val user = UserDone(record.value().id, record.value().name, isGood = true)
      send(user)
      println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}")
    })
  }

}
