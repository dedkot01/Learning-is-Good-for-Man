package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise24Spec:
  @Test def testHasSubsequence: Unit =
    val l = List(1, 2, 3, 4, 5)
    val sub1 = List(1, 2)
    val sub2 = List(2, 3, 4)
    val sub3 = List(5, 6)
    assertEquals(true, hasSubsequence(l, sub1))
    assertEquals(true, hasSubsequence(l, sub2))
    assertEquals(false, hasSubsequence(l, sub3))
