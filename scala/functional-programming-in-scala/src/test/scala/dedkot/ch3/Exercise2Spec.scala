package dedkot.ch3

import org.junit.Test
import org.junit.Assert.*

class Exercise2Spec:
  @Test def normalList(): Unit = 
    val list = List(1, 2, 3, 4, 5)
    val expectedList = List(2, 3, 4, 5)
    assertEquals(expectedList, tail(list))

  @Test def emptyList(): Unit = 
    val list = List()
    val expectedList = Nil
    assertEquals(expectedList, tail(list))
