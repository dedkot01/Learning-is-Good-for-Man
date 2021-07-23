package dedkot.ch6

import scala.annotation.tailrec

trait RNG:
  def nextInt: (Int, RNG) // Should generate a random `Int`. We'll later define other functions in terms of `nextInt`.

object RNG:
  // NB - this was called SimpleRNG in the book text

  case class Simple(seed: Long) extends RNG:
    def nextInt: (Int, RNG) =
      val newSeed = (seed * 0x5DEECE66DL + 0xBL) & 0xFFFFFFFFFFFFL // `&` is bitwise AND. We use the current seed to generate a new seed.
      val nextRNG = Simple(newSeed) // The next state, which is an `RNG` instance created from the new seed.
      val n = (newSeed >>> 16).toInt // `>>>` is right binary shift with zero fill. The value `n` is our new pseudo-random integer.
      (n, nextRNG) // The return value is a tuple containing both a pseudo-random integer and the next `RNG` state.

  type Rand[+A] = RNG => (A, RNG)

  val int: Rand[Int] = _.nextInt

  def unit[A](a: A): Rand[A] =
    rng => (a, rng)

  def map[A,B](s: Rand[A])(f: A => B): Rand[B] =
    rng => {
      val (a, rng2) = s(rng)
      (f(a), rng2)
    }

  @tailrec
  def nonNegativeInt(rng: RNG): (Int, RNG) = rng.nextInt match
    case i if (i._1 >= 0 && i._1 <= Int.MaxValue) => i
    case i => nonNegativeInt(i._2)

  def double(rng: RNG): (Double, RNG) =
    val i = nonNegativeInt(rng)
    (i._1 / (Int.MaxValue.toDouble + 1), i._2)

  def doubleViaMap: Rand[Double] =
    map(nonNegativeInt)(_ / (Int.MaxValue.toDouble + 1))

  def intDouble(rng: RNG): ((Int,Double), RNG) =
    val i = nonNegativeInt(rng)
    val d = double(rng)
    ((i._1, d._1), i._2)

  def doubleInt(rng: RNG): ((Double,Int), RNG) =
    intDouble(rng) match
      case ((i, d), r) => ((d, i), r)

  def double3(rng: RNG): ((Double,Double,Double), RNG) =
    val d1 = double(rng)
    val d2 = double(d1._2)
    val d3 = double(d2._2)
    ((d1._1, d2._1, d3._1), d3._2)

  def ints(count: Int)(rng: RNG): (List[Int], RNG) =
    def next(n: Int, rng: RNG, l: List[Int]): (List[Int], RNG) =
      if (n <= 0) (l, rng)
      else {
        val i = nonNegativeInt(rng)
        next(n - 1, i._2, i._1 :: l)
      }
    next(count, rng, List())

  def map2[A,B,C](ra: Rand[A], rb: Rand[B])(f: (A, B) => C): Rand[C] = ???

  def sequence[A](fs: List[Rand[A]]): Rand[List[A]] = ???

  def flatMap[A,B](f: Rand[A])(g: A => Rand[B]): Rand[B] = ???

case class State[S,+A](run: S => (A, S)):
  def map[B](f: A => B): State[S, B] =
    ???
  def map2[B,C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
    ???
  def flatMap[B](f: A => State[S, B]): State[S, B] =
    ???

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

object State:
  type Rand[A] = State[RNG, A]
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = ???
