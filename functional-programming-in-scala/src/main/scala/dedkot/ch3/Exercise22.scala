package dedkot.ch3

def addPairwise(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match
  case (_, Nil) => l1
  case (Nil, _) => l2
  case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addPairwise(t1, t2)) 
