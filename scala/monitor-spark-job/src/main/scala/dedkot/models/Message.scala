package dedkot.models

import org.apache.spark.sql.streaming.{ StreamingQueryProgress, StreamingQueryStatus }
import org.json4s.jackson.JsonMethods.{ compact, parse, render }
import org.json4s.{ Formats, NoTypeHints }
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

import java.time.{ ZoneOffset, ZonedDateTime }

case class Message(source: String, dateTime: String, message: SparkStatus)

object Message {
  def apply(appName: String, status: StreamingQueryStatus, lastProgress: StreamingQueryProgress): Message = Message(
    source = "spark",
    dateTime = ZonedDateTime.now(ZoneOffset.UTC).toString,
    message = SparkStatus(appName, status, lastProgress)
  )

  def json(msg: Message): String = {
    implicit val formats: Formats = Serialization.formats(NoTypeHints)
    write(msg)
  }

  def jsonSnake(msg: Message): String = {
    compact(render(parse(json(msg)).snakizeKeys))
  }
}
