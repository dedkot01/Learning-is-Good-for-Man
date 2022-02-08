import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

implicit val formats: Formats = Serialization.formats(NoTypeHints)

case class MessageFromSpark(status: String)

case class Msg(source: String, date_time: String, message: MessageFromSpark)

val time = ZonedDateTime.now(ZoneOffset.UTC).toString

val test = Msg("spark", time, MessageFromSpark("Ok"))

val res = write(test)

val topics = "topic1,topic2"
topics.split(',').toSeq
