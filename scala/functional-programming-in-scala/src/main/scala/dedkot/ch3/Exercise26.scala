package dedkot.ch3

def maximum(t: Tree[Int]): Int = t match
  case Leaf(v) => v
  case Branch(l, r) => maximum(l) max maximum(r)
