package dedkot.ch3

def forEachFromDoubleToString(l: List[Double]): List[String] =
  List.foldRight(l, Nil:List[String])((h, t) => Cons(h.toString, t))
