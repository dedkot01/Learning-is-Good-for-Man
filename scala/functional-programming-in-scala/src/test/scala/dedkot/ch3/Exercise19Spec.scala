package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise19Spec:
  @Test def testFilter: Unit =
    val l = List(1, 2, 3, 4, 5)
    val expectedList = List(2, 4)
    assertEquals(expectedList, filter(l)(_ % 2 == 0))
