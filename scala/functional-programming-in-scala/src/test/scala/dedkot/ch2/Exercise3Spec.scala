package dedkot.ch2

import org.junit.Test
import org.junit.Assert.*

class Exercise3Spec:
  @Test def t1(): Unit = 
    val f = curry[Int, Int, Int]((a, b) => b - a)
    val minusOneFrom = f(1)
    assertEquals(1, minusOneFrom(2))
