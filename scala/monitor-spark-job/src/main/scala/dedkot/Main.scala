package dedkot

import dedkot.kafka.Producer
import dedkot.models.Message
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.{ DataFrame, SparkSession }

import java.lang.Thread.sleep

object Main extends App {
  def run: Unit = {
    val spark = SparkSession.builder().appName("monitor-spark-job").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "eva00")
      .load()
      .selectExpr("CAST(value AS STRING) as value")

    val query = df.writeStream.foreachBatch { (batchDF: DataFrame, _: Long) =>
      batchDF.show()
    }.start()

    monitoring(
      spark,
      query,
      kafkaBrokers = "localhost:9092",
      kafkaTopic = "monitor",
      updatePeriodInMillis = 5000
    )
  }

  def monitoring(
    spark: SparkSession,
    query: StreamingQuery,
    kafkaBrokers: String,
    kafkaTopic: String,
    updatePeriodInMillis: Long
  ): Unit = {
    val producer = Producer(kafkaBrokers, kafkaTopic)
    while (true) {
      sleep(updatePeriodInMillis)
      producer.send(Message.jsonSnake(Message(spark.sparkContext.appName, query.status, query.lastProgress)))
    }
    producer.close()
  }

  run
}
