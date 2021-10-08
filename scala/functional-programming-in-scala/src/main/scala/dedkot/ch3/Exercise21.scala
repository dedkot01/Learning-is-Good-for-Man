package dedkot.ch3

def filter1[A](l: List[A])(f: A => Boolean): List[A] =
  flatMap(l)(x => if (f(x)) List(x) else Nil)
