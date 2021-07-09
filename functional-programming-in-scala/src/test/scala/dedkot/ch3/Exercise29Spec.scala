package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise29Spec:
  @Test def testFold: Unit =
    val l = Leaf(1)
    assertEquals(1, fold(l)(identity)(_ + _))

    val b = Branch(l, l)
    assertEquals(2, fold(b)(identity)(_ + _))

    val t = Branch(l, b)
    assertEquals(3, fold(t)(identity)(_ + _))

    val t2 = Branch(b, b)
    assertEquals(4, fold(t2)(identity)(_ + _))
