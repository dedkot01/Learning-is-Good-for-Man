package dedkot.ch3

def map2[A, B](t: Tree[A])(f: A => B): Tree[B] = t match
  case Leaf(v) => Leaf(f(v))
  case Branch(l, r) => Branch(map2(l)(f), map2(r)(f))
