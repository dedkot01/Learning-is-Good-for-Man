package dedkot.ch3

def filter[A](as: List[A])(f: A => Boolean): List[A] =
  List.foldRight(as, Nil:List[A])((h, t) => if (f(h)) Cons(h, t) else t)
