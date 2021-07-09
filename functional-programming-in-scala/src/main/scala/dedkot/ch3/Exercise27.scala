package dedkot.ch3

def depth[A](t: Tree[A]): Int = t match
  case Leaf(_) => 1
  case Branch(l, r) => (1 + depth(l)) max (1 + depth(r))
