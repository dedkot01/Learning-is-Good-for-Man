package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise18Spec:
  @Test def testMap: Unit =
    val l = List(1, 2, 3)
    val expectedList = List(3, 4, 5)
    assertEquals(expectedList, map(l)(_ + 2))
