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

case class Some[+A](get: A) extends Option[A]
case object None extends Option[Nothing]
