package dedkot.kafka

import org.apache.kafka.clients.CommonClientConfigs
import org.apache.kafka.clients.producer.{ KafkaProducer, ProducerConfig, ProducerRecord }
import org.apache.kafka.common.serialization.StringSerializer

import java.util.Properties

class Producer(bootstrapServers: String, topic: String) {
  private val settings = new Properties
  settings.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers)
  settings.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")
  settings.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer")

  val producer = new KafkaProducer[String, String](settings, new StringSerializer(), new StringSerializer())

  def send(msg: String): Unit = {
    producer.send(new ProducerRecord[String, String](topic, msg))
  }

  def close(): Unit = producer.close()
}

object Producer {
  def apply(bootstrapServers: String, topic: String): Producer = new Producer(bootstrapServers, topic)
}
