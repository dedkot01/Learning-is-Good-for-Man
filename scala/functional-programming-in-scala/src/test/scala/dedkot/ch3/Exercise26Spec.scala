package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise26Spec:
  @Test def testMaximum: Unit =
    val l = Leaf(1)
    assertEquals(1, maximum(l))

    val l3 = Leaf(3)
    val b = Branch(l3, l)
    assertEquals(3, maximum(b))

    val t = Branch(l, b)
    assertEquals(3, maximum(t))

    val t2 = Branch(b, b)
    assertEquals(3, maximum(t2))
