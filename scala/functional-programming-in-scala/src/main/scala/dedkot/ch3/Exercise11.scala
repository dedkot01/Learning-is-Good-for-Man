package dedkot.ch3

def sum(as: List[Int]) = foldLeft(as, 0)(_ + _)

def product(as: List[Double]) = foldLeft(as, 1.0)(_ * _)

def length1[A](as: List[A]): Int = foldLeft(as, 0)((acc, _) => acc + 1)
