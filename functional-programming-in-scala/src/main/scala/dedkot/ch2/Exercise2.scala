package dedkot.ch2

import scala.annotation.tailrec

def isSorted[A](as: Array[A], ordered: (A, A) => Boolean): Boolean =
  @tailrec def loop(i: Int): Boolean =
    if (i >= as.length) true
    else if (!ordered(as(i), as(i - 1))) false
    else loop(i + 1)

  if (as.length <= 1) true
  else loop(1)
