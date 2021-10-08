package org.dedkot

import org.apache.flink.streaming.api.scala.{ createTypeInformation, StreamExecutionEnvironment }
import org.dedkot.function.CassandraBatchSink

import java.util.Date
import scala.io.Source

object CassandraBatchSinkApp extends App {
  val bufferedSource = Source.fromFile(
    "C:\\Users\\dzhdanov\\Project\\learning-is-good-for-man\\simple-cassandra-clients\\src\\main\\resources\\citys.csv"
  )
  val citys = for (line <- bufferedSource.getLines) yield {
    val cols = line.split(",").map(_.trim)
    City(cols(0), cols(1).toInt)
  }

  val env = StreamExecutionEnvironment.getExecutionEnvironment

  val city = env
    .fromElements(citys.toList)
    .addSink(new CassandraBatchSink)

  env.execute()
}
