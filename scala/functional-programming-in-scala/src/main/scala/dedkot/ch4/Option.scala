package dedkot.ch4

import scala.{Option => _, Either => _, Some => _, None => _, _}

sealed trait Option[+A]:
  def map[B](f: A => B): Option[B] = this match
    case None => None
    case Some(v) => Some(f(v))

  def flatMap[B](f: A => Option[B]): Option[B] = this match
    case None => None
    case Some(v) => f(v)

  def getOrElse[B>:A](default: => B): B = this match
    case None => default
    case Some(v) => v

  def orElse[B>:A](ob: => Option[B]): Option[B] = this match
    case None => ob
    case Some(v) => this

  def filter(f: A => Boolean): Option[A] = this match
    case None => None
    case Some(v) => if (f(v)) this else None

object Option:
  def mean(xs: Seq[Double]): Option[Double] =
    if (xs.isEmpty) None
    else Some(xs.sum / xs.length)

  def variance(xs: Seq[Double]): Option[Double] =
    mean(xs) flatMap (m => mean(xs.map(x => math.pow(x - m, 2))))

  def map2[A,B,C](a: Option[A], b: Option[B])(f: (A, B) => C): Option[C] =
    a.flatMap(v => b.map(v2 => f(v, v2)))

  def sequence[A](a: List[Option[A]]): Option[List[A]] = a match
    case Nil => Some(Nil)
    case h :: t => h.flatMap(h2 => sequence(t).map(h2 :: _))

  def traverse[A, B](a: List[A])(f: A => Option[B]): Option[List[B]] = a match
    case Nil => Some(Nil)
    case h :: t => map2(f(h), traverse(t)(f))(_ :: _)

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]
