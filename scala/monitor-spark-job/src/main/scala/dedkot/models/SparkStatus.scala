package dedkot.models

import org.apache.spark.sql.streaming.{ SourceProgress, StreamingQueryProgress, StreamingQueryStatus }

case class SparkStatus(appName: String, message: String, sources: Array[SourceProgress])

object SparkStatus {
  def apply(appName: String, status: StreamingQueryStatus, lastProgress: StreamingQueryProgress): SparkStatus =
    SparkStatus(
      appName = appName,
      message = status.message,
      sources = lastProgress.sources
    )
}
