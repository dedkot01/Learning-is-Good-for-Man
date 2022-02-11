package dedkot

import dedkot.kafka.Producer
import dedkot.models.Report
import org.apache.spark.sql.functions.from_json
import org.apache.spark.sql.streaming.StreamingQuery
import org.apache.spark.sql.types.StructType
import org.apache.spark.sql.{ DataFrame, SparkSession }

import java.lang.Thread.sleep

object Main extends App {
  def run(): Unit = {
    val spark = SparkSession.builder().appName("monitor-spark-job").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", "eva00")
      .load()
      .selectExpr("CAST(value AS STRING) as value")

    val query = df.writeStream.foreachBatch { (batchDF: DataFrame, _: Long) =>
      val schema = getDFSchemaFromJson(spark, batchDF, "value")
      monitoringCorruptMessage(spark.sparkContext.appName, batchDF, schema, "localhost:9092", "monitorCorrupt")

      batchDF.show()
    }.option("checkpointLocation", "./checkpoints").start()

    monitoring(
      spark.sparkContext.appName,
      query,
      kafkaBrokers = "localhost:9092",
      kafkaTopic = "monitor",
      updatePeriodInMillis = 5000
    )
  }

  def monitoringCorruptMessage(
    appName: String,
    df: DataFrame,
    schema: StructType,
    kafkaBrokers: String,
    kafkaTopic: String
  ): Unit = {
    val jsonDF = df
      .select(df("value"), from_json(df("value"), schema).as("json"))
      .filter("json is null")

    jsonDF.foreach { row =>
      val producer = Producer(kafkaBrokers, kafkaTopic)
      producer.send(Report.jsonSnake(Report(appName, row.getAs[String]("value"))))
      producer.close()
    }
  }

  def monitoring(
    appName: String,
    query: StreamingQuery,
    kafkaBrokers: String,
    kafkaTopic: String,
    updatePeriodInMillis: Long
  ): Unit = {
    val producer = Producer(kafkaBrokers, kafkaTopic)
    while (true) {
      sleep(updatePeriodInMillis)
      producer.send(Report.jsonSnake(Report(appName, query.status, query.lastProgress)))
    }
    producer.close()
  }

  def getDFSchemaFromJson(spark: SparkSession, df: DataFrame, columnName: String): StructType = {
    import spark.implicits._
    spark.read.json(df.select(columnName).as[String]).schema
  }

  run()
}
