package dedkot.ch3

import scala.annotation.tailrec

@tailrec def foldLeft[A,B](as: List[A], z: B)(f: (B, A) => B): B =
  as match
    case Nil => z
    case Cons(h, t) => foldLeft(t, f(z, h))(f)
