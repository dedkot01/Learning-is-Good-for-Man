package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise15Spec:
  @Test def testConcat: Unit =
    val l = List(List(1, 2), List(3, 4, 5))
    val expectedList = List(1, 2, 3, 4, 5)
    assertEquals(expectedList, concat(l))
