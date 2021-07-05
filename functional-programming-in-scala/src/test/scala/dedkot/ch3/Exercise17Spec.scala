package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise17Spec:
  @Test def t1: Unit =
    val l = List(1.0, 2.0, 3.0)
    val expectedList = List("1.0", "2.0", "3.0")
    assertEquals(expectedList, forEachFromDoubleToString(l))
