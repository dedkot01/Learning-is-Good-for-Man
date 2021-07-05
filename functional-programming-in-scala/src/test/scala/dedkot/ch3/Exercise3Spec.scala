package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise3Spec:
  @Test def normalList(): Unit = 
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(6, 2, 3, 4, 5)
    assertEquals(expectedList, setHead(list, 6))

  @Test def emptyList(): Unit = 
    val list = List()
    val expectedList = List(6)
    assertEquals(expectedList, setHead(list, 6))
