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

func main() {
	task2()
}
