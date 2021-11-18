class Animal(val name: String)

class Dog(name: String) extends Animal(name) {
  def woof(): Unit = print(s"$name - woof")
}

class Cat(name: String) extends Animal(name) {
  def meow(): Unit = print(s"$name - meow")
}

class Human(val name: String)

class Student(name: String) extends Human(name) {
  def doHomework(): Unit = print(s"$name do homework")
}

class Teacher(name: String) extends Human(name) {
  def teaching(): Unit = print(s"$name teaching")
}

def printHumanNames(l: List[Human]): Unit = l.foreach(x => println(x.name))

def printAnimalNames(l: List[Animal]): Unit = l.foreach(x => println(x.name))

val lHumans: List[Human] = List(new Student("Dima"), new Teacher("Tatiana"))
val lAnimals: List[Animal] = List(new Dog("Sharik"), new Cat("Murzik"))

print(lHumans)
print(lAnimals)

printHumanNames(lHumans)
print("------------")
printAnimalNames(lAnimals)

print("============")

printHumanNames(lAnimals)
print("------------")
printAnimalNames(lHumans)

