import com.softwaremill.quicklens._

case class Address(city: String, street: String)
case class User(name: String, address: Address)

val user1 = User("Naruto", Address("Konoha", "Pushkina"))
val user2 = user1.modify(_.address.city).setTo("Tokyo-3")

case class Journal(users: Seq[User])

val journal1 = Journal(Seq(user1, user2))
journal1.modify(_.users.eachWhere(_.address.city == "Tokyo-3").name).setTo("Kaoru")
journal1.modify(_.users.each.name).setTo("Sasuke")

case class MaybeJournal(maybeJournal: Option[Journal])

val mJ1 = MaybeJournal(None)
mJ1.modify(_.maybeJournal.each.users.each.name).setTo("Asuka")

val mJ2 = MaybeJournal(Option(journal1))
mJ2.modify(_.maybeJournal.each.users.each.name).setTo("Ayanami")
