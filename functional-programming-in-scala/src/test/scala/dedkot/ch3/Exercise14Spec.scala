package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise14Spec:
  @Test def testAppend: Unit =
    val l1 = List(1, 2)
    val l2 = List(3, 4, 5)
    val expectedList = List(1, 2, 3, 4, 5)
    assertEquals(expectedList, append(l1, l2))
