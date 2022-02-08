package dedkot

import dedkot.kafka.Producer
import dedkot.models.Message
import org.apache.spark.sql.{ DataFrame, SparkSession }

import java.lang.Thread.sleep

object Main extends App {
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

  val producer = Producer("localhost:9092", Seq("monitor"))
  while (true) {
    sleep(5000)
    producer.send(Message.jsonSnake(Message(spark.sparkContext.appName, query.status, query.lastProgress)))
  }
}
