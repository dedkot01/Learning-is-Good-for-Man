import org.json4s._
import org.json4s.jackson.Serialization
import org.json4s.jackson.Serialization.write

import java.time.{LocalDateTime, ZoneId, ZoneOffset, ZonedDateTime}

implicit val formats: Formats = Serialization.formats(NoTypeHints)

trait Message

case class MessageFromSpark(status: String) extends Message

case class MessageFromKafka(typeMessage: String) extends Message

case class Msg(source: String, date_time: String, message: Message)

val time = ZonedDateTime.now(ZoneOffset.UTC).toString

val test = Msg("spark", time, MessageFromSpark("Ok"))

val res = write(test)

val test2 = Msg("kafka", time, MessageFromKafka("Fail"))

val res2 = write(test2)

val topics = "topic1,topic2"
topics.split(',').toSeq
