package dedkot.ch4

sealed trait MyEither[+E, +A]:
  def map[B](f: A => B): MyEither[E, B] = this match
    case MyLeft(e) => MyLeft(e)
    case MyRight(v) => MyRight(f(v))

  def flatMap[EE >: E, B](f: A => MyEither[EE, B]): MyEither[EE, B] = this match
    case MyLeft(e) => MyLeft(e)
    case MyRight(v) => f(v)
  
  def orElse[EE >: E,B >: A](b: => MyEither[EE, B]): MyEither[EE, B] = this match
    case MyRight(v) => MyRight(v)
    case MyLeft(_) => b
  
  def map2[EE >: E, B, C](b: MyEither[EE, B])(f: (A, B) => C): MyEither[EE, C] =
    for {x1 <- this; x2 <- b} yield f(x1, x2)

object MyEither {
  def traverse[E, A, B](as: List[A])(f: A => MyEither[E, B]): MyEither[E, List[B]] = as match
    case Nil => MyRight(Nil)
    case h::t => (f(h).map2(traverse(t)(f))(_ :: _))

  def sequence[E, A](es: List[MyEither[E, A]]): MyEither[E, List[A]] = traverse(es)(x => x)
}

case class MyLeft[+E](value: E) extends MyEither[E, Nothing]
case class MyRight[+A](value: A) extends MyEither[Nothing, A]
