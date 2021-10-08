/*
package org.dedkot

import com.datastax.driver.core.Cluster
import org.apache.flink.api.java.tuple.Tuple
import org.apache.flink.api.scala.{ createTypeInformation, ExecutionEnvironment }
import org.apache.flink.batch.connectors.cassandra.CassandraTupleOutputFormat
import org.apache.flink.streaming.connectors.cassandra.ClusterBuilder

import java.util.Date
import scala.io.Source

object CassandraBatchConnectorApp extends App {
  val bufferedSource = Source.fromFile(
    "C:\\Users\\dzhdanov\\Project\\learning-is-good-for-man\\simple-cassandra-clients\\src\\main\\resources\\citys.csv"
  )
  val citys = for (line <- bufferedSource.getLines) yield {
    val cols = line.split(",").map(_.trim)
    City(cols(0), cols(1).toInt)
  }

  val startTime = new Date().getTime

  val env = ExecutionEnvironment.getExecutionEnvironment

  val city = env
    .fromElements(citys.toList)

  val sink =
    new CassandraTupleOutputFormat[City]("INSERT INTO db.city (name, count) VALUES (?, ?);", new ClusterBuilder {
      override def buildCluster(builder: Cluster.Builder): Cluster = builder.addContactPoint("127.0.0.1").build()
    })

  city.output(sink)

  env.execute()

  println(new Date().getTime - startTime)
}
*/
