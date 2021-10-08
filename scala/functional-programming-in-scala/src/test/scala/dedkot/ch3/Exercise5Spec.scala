package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise5Spec:
  @Test def normalList: Unit =
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(3, 4, 5)
    assertEquals(expectedList, dropWhile(list, _ < 3))
