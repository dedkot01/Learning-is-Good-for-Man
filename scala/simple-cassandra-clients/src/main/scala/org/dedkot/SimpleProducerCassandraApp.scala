package org.dedkot

import org.apache.flink.streaming.api.scala.{ createTypeInformation, StreamExecutionEnvironment }
import org.apache.flink.streaming.connectors.cassandra.CassandraSink

import java.util.Date

case class City(name: String, count: Int)

object SimpleProducerCassandraApp extends App {
  val env = StreamExecutionEnvironment.getExecutionEnvironment

  val city = env
    .readTextFile(
      "C:\\Users\\dzhdanov\\Project\\learning-is-good-for-man\\simple-cassandra-clients\\src\\main\\resources\\citys.csv"
    )
    .map { s =>
      val a = s.split(',').map(_.trim)
      City(a(0), a(1).toInt)
    }

  CassandraSink
    .addSink(city)
    .setQuery("INSERT INTO db.city(name, count) values (?, ?);")
    .setHost("127.0.0.1")
    .build()

  val startTime = new Date().getTime

  env.execute()

  println(new Date().getTime - startTime)
}
