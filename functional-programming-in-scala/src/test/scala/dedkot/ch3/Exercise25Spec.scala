package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise25Spec:
  @Test def testSize: Unit =
    val l = Leaf(1)
    assertEquals(1, size(l))

    val b = Branch(l, l)
    assertEquals(3, size(b))

    val t = Branch(l, b)
    assertEquals(5, size(t))

    val t2 = Branch(b, b)
    assertEquals(7, size(t2))
