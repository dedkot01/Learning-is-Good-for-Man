package dedkot.ch3

def map[A,B](as: List[A])(f: A => B): List[B] =
  List.foldRight(as, Nil:List[B])((h, t) => Cons(f(h), t))
