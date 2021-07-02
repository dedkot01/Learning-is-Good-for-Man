package dedkot.ch2

import org.junit.Test
import org.junit.Assert.*

class Exercise1Spec:
  @Test def t1(): Unit = 
    assertEquals(0, fib(0))
    assertEquals(1, fib(1))
    assertEquals(1, fib(2))
    assertEquals(2, fib(3))
    assertEquals(3, fib(4))
    assertEquals(5, fib(5))
    assertEquals(8, fib(6))
    assertEquals(13, fib(7))
    assertEquals(21, fib(8))
    assertEquals(34, fib(9))
