package dedkot.ch3

def fold[A,B](t: Tree[A])(f: A => B)(b: (B,B) => B): B = t match
  case Leaf(v) => f(v)
  case Branch(l, r) => b(fold(l)(f)(b), fold(r)(f)(b))
