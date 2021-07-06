package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise20Spec:
  @Test def testFlatMap: Unit =
    val l = List(1, 2, 3)
    val expectedList = List(1, 1, 2, 2, 3, 3)
    assertEquals(expectedList, flatMap(l)(i => List(i, i)))
