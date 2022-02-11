package dedkot.models

case class CorruptMessage(appName: String, record: String, typeMessage: String = "corrupt record") extends Message
