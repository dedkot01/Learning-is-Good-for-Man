package dedkot.ch6

import org.junit.Test
import org.junit.Assert.*

class StateSpec:
  val rngSimple1 = RNG.Simple(1)

  @Test def testNonNegativeInt: Unit =
    val expected = (384748, RNG.Simple(25214903928))
    assertEquals(expected, RNG.nonNegativeInt(rngSimple1))

  @Test def testDouble: Unit =
    val expected = (1.7916224896907806E-4, RNG.Simple(25214903928))
    assertEquals(expected, RNG.double(rngSimple1))

  @Test def testIntDouble: Unit =
    val expected = ((384748, 1.7916224896907806E-4), RNG.Simple(25214903928))
    assertEquals(expected, RNG.intDouble(rngSimple1))

  @Test def testDoubleInt: Unit =
    val expected = ((1.7916224896907806E-4, 384748), RNG.Simple(25214903928))
    assertEquals(expected, RNG.doubleInt(rngSimple1))

  @Test def testDouble3: Unit =
    val expected = ((1.7916224896907806E-4,0.7510961224325001,0.7282915939576924), RNG.Simple(102497929776471))
    assertEquals(expected, RNG.double3(rngSimple1))
