package main

import "fmt"

func task1() {
	var x int
	fmt.Scan(&x)
	
	switch {
	case x > 0:
		fmt.Println("Число положительное")
	case x < 0:
		fmt.Println("Число отрицательное")
	default:
		fmt.Println("Ноль")
	}
}

func task2() {
	var x int
	fmt.Scan(&x)

	a := x / 100
	b := x / 10 % 10
	c := x % 10

	if a != b && a != c && b != c {
		fmt.Println("YES")
	} else {
		fmt.Println("NO")
	}
}

func task3() {
	var x int
	fmt.Scan(&x)

	switch {
	case x < 10:
		fmt.Println(x)
	case x < 100:
		fmt.Println(x / 10)
	case x < 1000:
		fmt.Println(x / 100)
	case x < 10000:
		fmt.Println(x / 1000)
	case x < 100000:
		fmt.Println(x / 10000)
	}
}

func is_lucky_ticket() {
	var x int
	fmt.Scan(&x)

	if x % 10 + x % 100 / 10 + x % 1000 / 100 == x % 10000 / 1000 + x % 100000 / 10000 + x % 1000000 / 100000 {
		fmt.Println("YES")
	} else {
		fmt.Println("NO")
	}
}

func is_leap_year() {
	var x int
	fmt.Scan(&x)

	if (x % 400 == 0) || (x % 4 == 0 && x % 100 != 0) {
		fmt.Println("YES")
	} else {
		fmt.Println("NO")
	}
}

func main() {
	task3()
}
