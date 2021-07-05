package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise11Spec:
  @Test def testSum: Unit =
    val list = List(1, 2, 3, 4, 5)
    assertEquals(15, sum(list))

  @Test def testProduct: Unit =
    val list = List(1.0, 2.0, 3.0, 4.0, 5.0)
    assertEquals(120.0, product(list), 0)

  @Test def testLength: Unit =
    val list = List(1, 2, 3, 4, 5)
    assertEquals(5, length1(list))
