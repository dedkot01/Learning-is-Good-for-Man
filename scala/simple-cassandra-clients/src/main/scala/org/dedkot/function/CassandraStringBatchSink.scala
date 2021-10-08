package org.dedkot.function

import com.datastax.driver.core.{ BatchStatement, Cluster, SimpleStatement }
import org.apache.flink.streaming.api.functions.sink.SinkFunction
import org.dedkot.City

class CassandraStringBatchSink extends SinkFunction[List[City]] {
  override def invoke(value: List[City], context: SinkFunction.Context): Unit = {
    val cluster = Cluster.builder().addContactPoint("127.0.0.1").build()
    val session = cluster.connect("db")

    for (window <- value.sliding(500, 500)) {
      val batch = new StringBuffer("BEGIN BATCH ")
      for (city <- window) {
        batch.append(s"INSERT INTO city (name, count) VALUES ('${city.name}', ${city.count});")
      }
      batch.append(" APPLY BATCH;")
      session.execute(batch.toString)
    }
  }
}
