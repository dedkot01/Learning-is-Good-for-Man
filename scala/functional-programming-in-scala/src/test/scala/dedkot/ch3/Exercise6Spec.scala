package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise6Spec:
  @Test def t1: Unit =
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(1, 2, 3, 4)
    assertEquals(expectedList, init(list))
