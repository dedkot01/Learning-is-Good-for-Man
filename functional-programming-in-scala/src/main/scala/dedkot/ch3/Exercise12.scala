package dedkot.ch3

def reverse[A](l: List[A]): List[A] = foldLeft(l, List[A]())((a, b) => Cons(b, a))
