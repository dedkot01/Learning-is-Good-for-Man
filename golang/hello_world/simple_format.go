package main

import "fmt"

func task1() {
	var x float64
	fmt.Scan(&x)

	if x <= 0 {
		fmt.Printf("число %2.2f не подходит", x)
	} else if x > 10000 {
		fmt.Printf("%e", x)
	} else {
		fmt.Printf("%.4f", x * x)
	}
}

func main() {
	task1()
}
