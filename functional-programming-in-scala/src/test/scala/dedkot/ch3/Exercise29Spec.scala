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

  @Test def testSize: Unit =
    val l = Leaf(1)
    assertEquals(1, sizeViaFold(l))

    val b = Branch(l, l)
    assertEquals(3, sizeViaFold(b))

    val t = Branch(l, b)
    assertEquals(5, sizeViaFold(t))

    val t2 = Branch(b, b)
    assertEquals(7, sizeViaFold(t2))

  @Test def testMaximum: Unit =
    val l = Leaf(1)
    assertEquals(1, maximumViaFold(l))

    val l3 = Leaf(3)
    val b = Branch(l3, l)
    assertEquals(3, maximumViaFold(b))

    val t = Branch(l, b)
    assertEquals(3, maximumViaFold(t))

    val t2 = Branch(b, b)
    assertEquals(3, maximumViaFold(t2))
    
  @Test def testDepth: Unit =
    val l = Leaf(1)
    assertEquals(1, depthViaFold(l))

    val b = Branch(l, l)
    assertEquals(2, depthViaFold(b))

    val t = Branch(l, b)
    assertEquals(3, depthViaFold(t))

    val t2 = Branch(b, b)
    assertEquals(3, depthViaFold(t2))

  @Test def testMap: Unit =
    val t = Branch(Leaf(1), Branch(Leaf(2), Leaf(3)))
    val expectedTree = Branch(Leaf(2), Branch(Leaf(4), Leaf(6)))
    assertEquals(expectedTree, mapViaFold(t)(_ * 2))
