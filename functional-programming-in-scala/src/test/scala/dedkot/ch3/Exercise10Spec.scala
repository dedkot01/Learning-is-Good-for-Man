package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise10Spec:
  @Test def t1: Unit =
    val list = List(1, 2, 3, 4, 5)
    assertEquals(15, foldLeft(list, 0)(_ + _))
