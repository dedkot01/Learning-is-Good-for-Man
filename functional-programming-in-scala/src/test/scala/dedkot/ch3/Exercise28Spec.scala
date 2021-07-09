package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise28Spec:
  @Test def testMap: Unit =
    val t = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
    val expectedTree = Branch(Leaf(2), Branch(Leaf(4), Leaf(6)))
    assertEquals(expectedTree, map(t)(_ * 2))
