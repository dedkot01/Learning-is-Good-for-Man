package dedkot.ch3

def append[A](l1: List[A], l2: List[A]): List[A] =
  List.foldRight(l1, l2)(Cons(_, _))
