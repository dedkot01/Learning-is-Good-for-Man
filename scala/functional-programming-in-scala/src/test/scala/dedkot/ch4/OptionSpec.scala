package dedkot.ch4

import org.junit.Test
import org.junit.Assert.*

class OptionSpec:
  val none: dedkot.ch4.Option[Int] = dedkot.ch4.None
  val o1 = dedkot.ch4.Some(1)
  val o2 = dedkot.ch4.Some(2)

  @Test def testMap: Unit =
    assertEquals(none, none.map(_ + 1))
    assertEquals(o2, o1.map(_ + 1))

  @Test def testFlatMap: Unit =
    assertEquals(none, none.flatMap(x => dedkot.ch4.Some(x + 1)))
    assertEquals(o2, o1.flatMap(x => dedkot.ch4.Some(x + 1)))

  @Test def testGetOrElse: Unit =
    assertEquals(1, none.getOrElse(1))
    assertEquals(1, o1.getOrElse(2))

  @Test def testOrElse: Unit =
    assertEquals(o1, none.orElse(dedkot.ch4.Some(1)))
    assertEquals(o1, o1.orElse(dedkot.ch4.Some(2)))

  @Test def testFilter: Unit =
    assertEquals(none, none.filter(_ > 0))
    assertEquals(o1, o1.filter(_ > 0))
    assertEquals(none, o1.filter(_ > 1))

  @Test def testMean: Unit =
    assertEquals(None, Option.mean(Seq.empty))
    assertEquals(Some(1.5), Option.mean(Seq(1.0, 2.0)))

  @Test def testVariance: Unit =
    assertEquals(None, Option.variance(Seq.empty))
    assertEquals(Some(0.25), Option.variance(Seq(1.0, 2.0)))

  @Test def testMap2: Unit =
    assertEquals(None, Option.map2(None, None)((x, y) => x))
    assertEquals(o2, Option.map2(o1, o1)(_ + _))

  @Test def testSequence: Unit =
    assertEquals(Some(Nil), Option.sequence(Nil))
    assertEquals(None, Option.sequence(List(o1, none)))
    assertEquals(Some(List(1, 2)), Option.sequence(List(o1, o2)))

  @Test def testTraverse: Unit =
    assertEquals(Some(Nil), Option.traverse(List.empty)(Some(_)))
    assertEquals(Some(List(1, 2)), Option.traverse(List("1", "2"))(x => Some(x.toInt)))
