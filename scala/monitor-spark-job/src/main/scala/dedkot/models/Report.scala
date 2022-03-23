package dedkot.models

import org.apache.spark.sql.streaming.{ StreamingQueryProgress, StreamingQueryStatus }
import org.json4s.jackson.JsonMethods.{ compact, parse, render }
import org.json4s.{ Formats, NoTypeHints }
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

import java.time.{ ZoneOffset, ZonedDateTime }

case class Report(source: String, dateTime: String, message: Message)

object Report {
  def apply(appName: String, status: StreamingQueryStatus, lastProgress: StreamingQueryProgress): Report = Report(
    source = "spark",
    dateTime = ZonedDateTime.now(ZoneOffset.UTC).toString,
    message = SparkStatusMessage(appName, status, lastProgress)
  )

  def apply(appName: String, corruptMessage: String): Report = Report(
    source = "spark",
    dateTime = ZonedDateTime.now(ZoneOffset.UTC).toString,
    message = CorruptMessage(appName, corruptMessage)
  )

  def json(msg: Report): String = {
    implicit val formats: Formats = Serialization.formats(NoTypeHints)
    write(msg)
  }

  def jsonSnake(msg: Report): String = {
    compact(render(parse(json(msg)).snakizeKeys))
  }
}
