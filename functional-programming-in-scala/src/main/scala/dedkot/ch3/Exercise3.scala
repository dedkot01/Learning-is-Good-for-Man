package dedkot.ch3

def setHead[A](l: List[A], h: A): List[A] = l match 
  case Nil => List(h)
  case Cons(_, t) => Cons(h, t)
  