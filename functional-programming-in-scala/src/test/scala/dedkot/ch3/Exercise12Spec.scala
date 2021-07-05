package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise12Spec:
  @Test def testReverse: Unit =
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(5, 4, 3, 2, 1)
    assertEquals(expectedList, reverse(list))
