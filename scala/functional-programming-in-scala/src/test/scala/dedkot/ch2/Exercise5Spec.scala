package dedkot.ch2

import org.junit.Test
import org.junit.Assert.*

class Exercise5Spec:
  @Test def t1(): Unit = 
    val plus10: Int => Int = _ + 10 
    val mul3: Int => Int = _ * 3  
    val f = compose(plus10, mul3)
    assertEquals(19, f(3))
