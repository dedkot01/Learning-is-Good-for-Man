package org.dedkot

import java.io.{ BufferedWriter, File, FileWriter }

object SimpleWriterApp extends App {
  val file = new File(
    "C:\\Users\\dzhdanov\\Project\\learning-is-good-for-man\\simple-cassandra-clients\\src\\main\\resources\\citys.csv"
  )
  val bw = new BufferedWriter(new FileWriter(file))

  for (i <- 0 to 10000) bw.write(s"city-$i,$i\n")

  bw.close()
}
