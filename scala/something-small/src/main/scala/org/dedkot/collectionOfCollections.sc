val l = List(1, 2, 3, 4, 5, 6, 7, 8)
val i = l.sliding(2, 2)
for (el <- i) println(el)

val i2 = l.sliding(10, 10)
for (el <- i2) println(el)
