package dedkot.ch3

import scala.annotation.tailrec

@tailrec def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean =
  @tailrec def next(l1: List[A], l2: List[A]): Boolean = (l1, l2) match
    case (_, Nil) => true
    case (Cons(h1, t1), Cons(h2, t2)) if (h1 == h2) => next(t1, t2)
    case _ => false

  sup match 
    case Nil => sup == sub
    case _ if next(sup, sub) => true
    case Cons(_, t) => hasSubsequence(t, sub)