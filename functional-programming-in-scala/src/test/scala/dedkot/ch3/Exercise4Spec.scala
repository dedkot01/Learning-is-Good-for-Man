package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise4Spec:
  @Test def normalList(): Unit = 
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(3, 4, 5)
    assertEquals(expectedList, drop(list, 2))
