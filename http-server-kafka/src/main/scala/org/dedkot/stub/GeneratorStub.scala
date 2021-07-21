package org.dedkot.stub

import org.dedkot.kafka.Producer
import org.dedkot.model.UserNotDone

import scala.util.Random

object GeneratorStub extends App {
  var counter = 0
  val rand    = new Random()

  while (true) {
    val user = rand.nextInt(3) match {
      case 0 => UserNotDone(counter, "Naruto")
      case 1 => UserNotDone(counter, "Sasuke")
      case 2 => UserNotDone(counter, "Sakura")
      case _ => UserNotDone(counter, "Kakashi")
    }
    Producer.send(user)
    counter += 1
    Thread.sleep(5000)
  }
}
