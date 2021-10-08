def func(a: => Int): Int = a + a

def lazyFunc(a: => Int): Int = {
  lazy val b = a
  b + b
}

func(1)
func({println(1); 1})
lazyFunc({println(1); 1})
