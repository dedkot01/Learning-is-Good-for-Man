package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise27Spec:
  @Test def testDepth: Unit =
    val l = Leaf(1)
    assertEquals(1, depth(l))

    val b = Branch(l, l)
    assertEquals(2, depth(b))

    val t = Branch(l, b)
    assertEquals(3, depth(t))

    val t2 = Branch(b, b)
    assertEquals(3, depth(t2))
