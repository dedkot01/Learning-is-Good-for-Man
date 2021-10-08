package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise21Spec:
  @Test def testFilterViaFlatMap: Unit =
    val l = List(1, 2, 3, 4, 5)
    val expectedList = List(2, 4)
    assertEquals(expectedList, filter1(l)(_ % 2 == 0))
