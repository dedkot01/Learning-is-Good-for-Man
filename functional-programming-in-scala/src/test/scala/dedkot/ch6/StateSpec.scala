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
