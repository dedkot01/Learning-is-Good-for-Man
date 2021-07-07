package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise23Spec:
  @Test def testZipWith: Unit =
    val l1 = List("Di", "Di", "An")
    val l2 = List("ana", "ma", "ton")
    val expectedList = List("Diana", "Dima", "Anton")
    assertEquals(expectedList, zipWith(l1, l2)((a, b) => s"${a}${b}"))
