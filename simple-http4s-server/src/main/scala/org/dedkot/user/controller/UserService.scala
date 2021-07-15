package org.dedkot.user.controller

import cats.data.Kleisli
import cats.effect.IO
import cats.effect.unsafe.implicits.global
import io.circe.generic.auto._
import io.circe.syntax._
import org.dedkot.user.Database
import org.dedkot.user.model.User
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._

object UserService {
  implicit val decoder: EntityDecoder[IO, User] = jsonOf[IO, User]

  val userService: Kleisli[IO, Request[IO], Response[IO]] =
    HttpRoutes
      .of[IO] {
        case GET -> Root / "user" / name =>
          val user = Database.selectBy(name)
          user match {
            case Some(v) => Ok(v.asJson)
            case None    => NotFound(s"User with name $name not founded.")
          }

        case req @ POST -> Root / "user" / "add" =>
          val user: User = req.as[User].unsafeRunSync()
          Database.add(user)
          Ok("User added.")
      }
      .orNotFound
}
