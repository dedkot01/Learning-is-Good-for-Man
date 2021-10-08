package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise9Spec:
  @Test def t1: Unit =
    val list = List(1, 2, 3, 4, 5)
    assertEquals(5, length(list))
