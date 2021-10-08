package org.dedkot.stub

import com.sksamuel.avro4s.kafka.GenericSerde
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.{ ConsumerConfig, KafkaConsumer }
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }
import org.dedkot.config.{ KafkaConsumerConfig, KafkaProducerConfig }
import org.dedkot.model.{ UserDone, UserNotDone }

import java.time.Duration
import java.util
import java.util.Properties

object StreamletStub extends App {
  val conf           = ConfigFactory.load.getConfig("streamlet-stub")
  val consumerConfig = KafkaConsumerConfig(conf.getConfig("consumer"))
  val producerConfig = KafkaProducerConfig(conf.getConfig("producer"))

  val producerSettings = new Properties
  producerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, producerConfig.bootstrapServer)
  producerSettings.put(
    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.VoidSerializer"
  )
  producerSettings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, new GenericSerde[UserDone]())

  val consumerSettings = new Properties
  consumerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, consumerConfig.bootstrapServer)
  consumerSettings.put(ConsumerConfig.GROUP_ID_CONFIG, consumerConfig.groupId)
  consumerSettings.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true")
  consumerSettings.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000")
  consumerSettings.put(
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.VoidDeserializer"
  )
  consumerSettings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new GenericSerde[UserNotDone]())

  def send(user: UserDone): Unit = {
    val producer = new KafkaProducer[Unit, UserDone](producerSettings, null, new GenericSerde[UserDone]())
    producer.send(new ProducerRecord[Unit, UserDone](producerConfig.topic, user))
    producer.close()
  }

  val consumer = new KafkaConsumer[Unit, UserNotDone](consumerSettings, null, new GenericSerde[UserNotDone]())
  consumer.subscribe(util.Arrays.asList(consumerConfig.topic))

  while (true) {
    val records = consumer.poll(Duration.ofMillis(100))
    records.forEach(record => {
      val user = UserDone(record.value().id, record.value().name, isGood = true)
      send(user)
      println(s"offset = ${record.offset}, key = ${record.key}, value = ${record.value}")
    })
  }
}
