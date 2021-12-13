package org.dedkot

case class Animal(name: String)

class Dog(name: String) extends Animal(name) {
  def woof(): Unit = print(s"$name - woof")
}

class Cat(name: String) extends Animal(name) {
  def meow(): Unit = print(s"$name - meow")
}

case class Human(val name: String)

class Student(name: String) extends Human(name) {
  def doHomework(): Unit = print(s"$name do homework")
}

class Teacher(name: String) extends Human(name) {
  def teaching(): Unit = print(s"$name teaching")
}

object Polymorph extends App {
  def printNames[A](l: List[A]): Unit = l.foreach {
    case Animal(name) => println(name)
    case Human(name)  => println(name)
    case _            => "WTF?!"
  }

  val lHumans: List[Human]   = List(new Student("Dima"), new Teacher("Tatiana"))
  val lAnimals: List[Animal] = List(new Dog("Sharik"), new Cat("Murzik"))

  println(lHumans)
  println(lAnimals)

  printNames(lHumans)
  println("------------")
  printNames(lAnimals)

  println("============")

  printNames(lAnimals)
  println("------------")
  printNames(lHumans)
}
