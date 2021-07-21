package dedkot.ch5

import org.junit.Test
import org.junit.Assert.*

class StreamSpec:
  val l12 = List(1, 2)
  val l23 = List(2, 3)
  val l123 = List(1, 2, 3)
  val s123 = Stream(1, 2, 3)

  @Test def testToList: Unit =
    assertEquals(l123, s123.toList)
    assertEquals(List.empty, Stream().toList)

  @Test def testTake: Unit =
    assertEquals(l12, s123.take(2).toList)
    assertEquals(l123, s123.take(5).toList)

  @Test def testDrop: Unit =
    assertEquals(l23, s123.drop(1).toList)
    assertEquals(List.empty, s123.drop(3).toList)

  @Test def testTakeWhile: Unit =
    assertEquals(l12, s123.takeWhile(_ < 3).toList)
    assertEquals(l123, s123.takeWhile(_ < 4).toList)
    assertEquals(List.empty, s123.takeWhile(_ < 0).toList)

  @Test def testForAll: Unit =
    assertEquals(true, s123.forAll(_ < 4))
    assertEquals(false, s123.forAll(_ < 3))
