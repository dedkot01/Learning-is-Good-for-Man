package org.dedkot

import cats.effect._
import fs2.kafka._

object SimpleConsumerStream extends IOApp {
  override def run(args: List[String]): IO[ExitCode] = {
    val consumerSettings =
      ConsumerSettings[IO, Unit, String]
        .withAutoOffsetReset(AutoOffsetReset.Earliest)
        .withBootstrapServers("localhost:9092")
        .withGroupId("group")

    def processRecord(record: ConsumerRecord[Unit, String]): IO[Unit] =
      IO(println(s"Processing record: $record"))

    val stream =
      KafkaConsumer
        .stream(consumerSettings)
        .evalTap(_.subscribeTo("topic"))
        .flatMap(_.partitionedStream)
        .map { partitionStream =>
          partitionStream.evalMap { committable =>
            processRecord(committable.record)
          }
        }
        .parJoinUnbounded

    stream.compile.drain.as(ExitCode.Success)
  }
}
