package org.dedkot.function

import com.datastax.driver.core.{ BatchStatement, Cluster, Session, SimpleStatement }
import org.apache.flink.streaming.api.functions.sink.{ RichSinkFunction, SinkFunction }
import org.dedkot.{ CassandraSession, City }

import java.util.Date

class CassandraBatchSink extends SinkFunction[List[City]] with CassandraSession {
  override def invoke(value: List[City], context: SinkFunction.Context): Unit = {
    val cluster: Cluster = Cluster.builder().addContactPoint("127.0.0.1").build()
    val session: Session = cluster.connect("db")

    val startTime = new Date().getTime

    for (window <- value.sliding(500, 500)) {
      val batch = new BatchStatement()
      for (city <- window) {
        batch.add(new SimpleStatement(s"INSERT INTO city (name, count) VALUES ('${city.name}', ${city.count});"))
      }
      session.executeAsync(batch)
    }

    println(new Date().getTime - startTime)
  }
}
