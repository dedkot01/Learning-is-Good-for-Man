package org.dedkot.user

import org.dedkot.user.model.User

object Database {
  private var users: Seq[User] = Seq(
    User("Naruto", "Uzumaki", 18),
    User("Sasuke", "Uchiha", 18),
    User("Sakura", "Haruno", 18),
    User("Kakashi", "Hatake", 35)
  )

  def selectBy(firstName: String): Option[User] = {
    users.find(_.firstName == firstName)
  }

  def selectBy(age: Int): Option[User] = {
    users.find(_.age == age)
  }

  def add(user: User): Unit = {
    if (!(users contains user)) users = users :+ user
  }
}
