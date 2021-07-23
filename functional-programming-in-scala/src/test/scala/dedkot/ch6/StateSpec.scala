package dedkot.ch6

import org.junit.Test
import org.junit.Assert.*

class StateSpec:
  val rngSimple0 = RNG.Simple(0)
  val rngSimple0Expected = (0, RNG.Simple(11))

  @Test def testNonNegativeInt: Unit =
    assertEquals(rngSimple0Expected, RNG.nonNegativeInt(rngSimple0))
