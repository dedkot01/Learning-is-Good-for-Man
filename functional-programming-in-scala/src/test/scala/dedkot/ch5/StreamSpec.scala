package dedkot.ch5

import org.junit.Test
import org.junit.Assert.*

class StreamSpec:
  @Test def testToList: Unit =
    val l = List(1, 2, 3)
    val s = Stream(1, 2, 3)
    assertEquals(l, s.toList)
    assertEquals(List.empty, Stream().toList)
