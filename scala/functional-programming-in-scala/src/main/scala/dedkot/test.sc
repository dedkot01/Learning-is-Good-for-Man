1
def func(a: Int): Unit =
  println(s"func $a")

def laz(a: Int => Unit): Unit =
  a()

val x = func(3)
val x1 = laz(func(1))
