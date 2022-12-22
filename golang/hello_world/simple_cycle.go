package main

import "fmt"

func task1() {
	for i := 1; i <= 10; i++ {
		fmt.Println(i * i)
	}
}

func task2() {
	var a, b int
	fmt.Scan(&a)
	fmt.Scan(&b)

	var sum int = 0

	for i := a; i <= b; i++ {
		sum += i
	}

	fmt.Println(sum)
}

func task3() {
	var n_a, sum int = 0, 0

	fmt.Scan(&n_a)

	for i := 0; i < n_a; i++ {
		var x int
		fmt.Scan(&x)

		if x < 100 && x >= 10 && x % 8 == 0 {
			sum += x
		}
	}

	fmt.Println(sum)
}

func task4() {
	var x int

	var n_a, max int = 0, 0

	for fmt.Scan(&x); x != 0; fmt.Scan(&x) {
		switch {
		case x == max:
			n_a++
		case x > max:
			max = x
			n_a = 1
		}
	}

	fmt.Println(n_a)
}

func task5() {
	var n_a, c, d int
	fmt.Scan(&n_a)
	fmt.Scan(&c)
	fmt.Scan(&d)

	for i := 1; i <= n_a; i++ {
		if i % c == 0 && i % d != 0 {
			fmt.Println(i)
			break
		}
	}
}

func task6() {
	var x int

	for fmt.Scan(&x); x <= 100; fmt.Scan(&x) {
		if x >= 10 {
			fmt.Println(x)
		}
	}
}

func task7() {
	var x, p, y int
	fmt.Scan(&x)
	fmt.Scan(&p)
	fmt.Scan(&y)

	var res int = 0

	for i := x; i <= y; i = i + (i * p / 100) {
		res++
	}

	fmt.Println(res)
}

func main() {
	task7()
}
