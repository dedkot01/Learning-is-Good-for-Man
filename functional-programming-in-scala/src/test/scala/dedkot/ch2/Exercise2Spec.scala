package dedkot.ch2

import org.junit.Test
import org.junit.Assert.*

class Exercise2Spec:
  @Test def t1(): Unit = 
    assertEquals(true, isSorted[Int](Array(1, 2, 3, 4), (a, b) => a >= b))
    assertEquals(false, isSorted[Int](Array(1, 3, 2, 4), (a, b) => a >= b))
    assertEquals(true, isSorted[Int](Array(1), (a, b) => a >= b))
    assertEquals(true, isSorted[Int](Array.emptyIntArray, (a, b) => a >= b))
