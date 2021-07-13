package dedkot.ch4

import org.junit.Test
import org.junit.Assert.*

class MyEitherSpec:
  val l: MyEither[Int, Int] = MyLeft(1)
  val r: MyEither[Int, Int] = MyRight(1)
  val r2: MyEither[Int, Int] = MyRight(2)

  @Test def testMap: Unit =
    assertEquals(l, l.map(_ + 1))
    assertEquals(r2, r.map(_ + 1))

  @Test def testFlatMap: Unit =
    assertEquals(l, l.flatMap(x => MyRight(x + 1)))
    assertEquals(r2, r.flatMap(x => MyRight(x + 1)))

  @Test def testOrElse: Unit =
    assertEquals(r, l.orElse(r))
    assertEquals(r, r.orElse(r2))

  @Test def testMap2: Unit =
    assertEquals(l, l.map2(r)(_ + _))
    assertEquals(l, r.map2(l)(_ + _))
    assertEquals(r2, r.map2(r)(_ + _))
