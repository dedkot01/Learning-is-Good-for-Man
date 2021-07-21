package org.dedkot.kafka

import com.sksamuel.avro4s.kafka.GenericSerde
import com.typesafe.config.ConfigFactory
import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.consumer.{ ConsumerConfig, KafkaConsumer }
import org.dedkot.config.KafkaConsumerConfig
import org.dedkot.model.{ UserDone, UserNotDone }

import java.time.Duration
import java.util
import java.util.Properties

object Consumer {
  private val config           = KafkaConsumerConfig(ConfigFactory.load.getConfig("kafka-service.consumer"))
  private val consumerSettings = new Properties
  consumerSettings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, config.bootstrapServer)
  consumerSettings.put(ConsumerConfig.GROUP_ID_CONFIG, config.groupId)
  consumerSettings.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false")
  consumerSettings.put(
    ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
    "org.apache.kafka.common.serialization.VoidDeserializer"
  )
  consumerSettings.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, new GenericSerde[UserDone]())

  def get(user: UserNotDone): UserDone = {
    val consumer = new KafkaConsumer[Unit, UserDone](consumerSettings, null, new GenericSerde[UserDone]())
    consumer.subscribe(util.Arrays.asList(config.topic))

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
