package dedkot.ch5

import org.junit.Test
import org.junit.Assert.*

class StreamSpec:
  val l1    = List(1)
  val l12   = List(1, 2)
  val l23   = List(2, 3)
  val l123  = List(1, 2, 3)
  val s1    = Stream(1)
  val s12   = Stream(1, 2)
  val s23   = Stream(2, 3)
  val s123  = Stream(1, 2, 3)
  val s1s23 = Stream(s1, s23)

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

  @Test def testTakeWhileViaFoldRight: Unit =
    assertEquals(l12, s123.takeWhileViaFoldRight(_ < 3).toList)
    assertEquals(l1, s123.takeWhileViaFoldRight(_ < 2).toList)
    assertEquals(List.empty, s123.takeWhileViaFoldRight(_ < 0).toList)

  @Test def testHeadOption: Unit =
    assertEquals(Option(1), s123.headOption)
    assertEquals(None, Stream.empty.headOption)

  @Test def testMap: Unit =
    assertEquals(l23, s12.map(_ + 1).toList)

  @Test def testFilter: Unit =
    assertEquals(l1, s123.filter(_ < 2).toList)

  @Test def testAppend: Unit =
    assertEquals(l123, s1.append(s23).toList)

  @Test def testFlatMap: Unit =
    assertEquals(l123, s1s23.flatMap(x => x).toList)

  @Test def testConstant: Unit =
    assertEquals(List(3, 3, 3, 3, 3), Stream.constant(3).take(5).toList)
