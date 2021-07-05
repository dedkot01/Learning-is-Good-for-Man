package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise16Spec:
  @Test def t1: Unit =
    val l = List(1, 2, 3, 4, 5)
    val expectedList = List(2, 3, 4, 5, 6)
    assertEquals(expectedList, forEachPlusOne(l))
