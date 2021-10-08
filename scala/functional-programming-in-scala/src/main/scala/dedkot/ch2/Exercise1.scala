package dedkot.ch2

import scala.annotation.tailrec

def fib(n: Int): Int =
  @tailrec def next(index: Int, prev: Int, cur: Int): Int =
    if (index == 0) prev
    else next(index - 1, cur, cur + prev)

  next(n, 0, 1)
