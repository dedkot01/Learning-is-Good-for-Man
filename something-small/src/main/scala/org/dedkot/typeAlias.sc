type Client = (Int, String)

val cl1: Client = (1, "Naruto")
val cl2 = (2, "Sasuke")

cl2 match {
  case it: Client => s"${it._2} is client"
  case _ => "Unknown"
}

type HardFunc = Int => Int => Int

val add: HardFunc = x => y => x + y

add(1)(2)

val mult: Int => Int => Int = x => y => x * y

mult(2)(3)
