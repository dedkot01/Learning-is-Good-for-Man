package dedkot.ch3

def dropWhile[A](l: List[A], f: A => Boolean): List[A] =
  l match
    case Nil => Nil
    case Cons(h, t) =>
      if (!f(h)) l
      else dropWhile(t, f)
