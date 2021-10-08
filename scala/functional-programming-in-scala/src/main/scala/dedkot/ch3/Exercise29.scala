package dedkot.ch3

def fold[A,B](t: Tree[A])(f: A => B)(b: (B,B) => B): B = t match
  case Leaf(v) => f(v)
  case Branch(l, r) => b(fold(l)(f)(b), fold(r)(f)(b))

def sizeViaFold[A](t: Tree[A]): Int = fold(t)(_ => 1)(1 + _ + _)

def maximumViaFold(t: Tree[Int]): Int = fold(t)(identity)(_ max _)

def depthViaFold[A](t: Tree[A]): Int = fold(t)(v => 1)((l, r) => 1 + (l max r))

def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] = 
  fold(t)(v => Leaf(f(v)): Tree[B])((l, r) => Branch(l, r))
