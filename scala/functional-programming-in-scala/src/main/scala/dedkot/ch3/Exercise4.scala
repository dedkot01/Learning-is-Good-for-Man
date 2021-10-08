package dedkot.ch3

import scala.annotation.tailrec

@tailrec def drop[A](l: List[A], n: Int): List[A] =
  if (n == 0) l
  else drop(tail(l), n - 1)
