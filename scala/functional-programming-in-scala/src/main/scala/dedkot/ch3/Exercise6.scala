package dedkot.ch3

def init[A](l: List[A]): List[A] =
  l match
    case Cons(h, t) if t != Nil => Cons(h, init(t))
    case _ => Nil
