package dedkot.ch3

def tail[A](l: List[A]): List[A] = l match
  case Nil => Nil
  case Cons(h, t) => t
