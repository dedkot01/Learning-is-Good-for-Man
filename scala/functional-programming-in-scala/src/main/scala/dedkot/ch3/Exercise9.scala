package dedkot.ch3

def length[A](as: List[A]): Int = List.foldRight(as, 0)((_, acc) => acc + 1)
