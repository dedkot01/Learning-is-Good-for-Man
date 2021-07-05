package dedkot.ch3

@main def main(): Unit =
  println(List.foldRight(List(1,2,3), Nil:List[Int])(Cons(_,_)))
