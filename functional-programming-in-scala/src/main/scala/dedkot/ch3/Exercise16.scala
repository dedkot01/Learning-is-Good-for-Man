package dedkot.ch3

def forEachPlusOne(l: List[Int]): List[Int] =
  List.foldRight(l, Nil:List[Int])((h, t) => Cons(h + 1, t))
