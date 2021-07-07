package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise22Spec:
  @Test def testAddPairwise: Unit =
    val l1 = List(1, 2, 3)
    val l2 = List(4, 5, 6)
    val expectedList = List(5, 7, 9)
    assertEquals(expectedList, addPairwise(l1, l2))
    assertEquals(l1, addPairwise(l1, Nil))
    assertEquals(l2, addPairwise(Nil, l2))
