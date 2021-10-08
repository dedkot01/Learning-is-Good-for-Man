package dedkot.ch3

def concat[A](l: List[List[A]]): List[A] =
  List.foldRight(l, Nil:List[A])(append)
