package dedkot.models

import org.apache.spark.sql.streaming.{ SourceProgress, StreamingQueryProgress, StreamingQueryStatus }

case class SparkStatusMessage(
  appName: String,
  status: String,
  sources: Array[SourceProgress],
  typeMessage: String = "spark status")
    extends Message

object SparkStatusMessage {
  def apply(appName: String, status: StreamingQueryStatus, lastProgress: StreamingQueryProgress): SparkStatusMessage =
    SparkStatusMessage(
      appName = appName,
      status = status.message,
      sources = lastProgress.sources
    )
}
