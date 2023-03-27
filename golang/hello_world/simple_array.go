package main

import "fmt"

func task1() {
	var workArray [10]uint8

	for i := 0; i < 10; i++ {
		var x uint8
		fmt.Scan(&x)

		workArray[i] = x
	}

	for i := 0; i < 3; i++ {
		var a uint8
		var b uint8

		fmt.Scan(&a)
		fmt.Scan(&b)

		tmp := workArray[a]
		workArray[a] = workArray[b]
		workArray[b] = tmp
	}

	for i := 0; i < 10; i++ {
		fmt.Print(workArray[i])
		fmt.Print(" ")
	}
}

func task2() {
	var a []int
	var n int
	fmt.Scan(&n)

	for i := 0; i < n; i++ {
		var x int
		fmt.Scan(&x)

		a = append(a, x)
	}

	fmt.Println(a[3])
}

func task3() {
	array := [5]int{}
	var a int
	for i:=0; i < 5; i++{
		fmt.Scan(&a)
		array[i] = a
	}

	max := array[0]

	for i := 1; i < 5; i++ {
		if array[i] > max {
			max = array[i]
		}
	}

	fmt.Println(max)
}

func task4() {
	var n int
	fmt.Scan(&n)
	var arr []int
	for i := 0; i < n; i++ {
		var x int
		fmt.Scan(&x)

		arr = append(arr, x)
	}

	for i := 0; i < n; i += 2 {
		fmt.Print(arr[i])
		fmt.Print(" ")
	}
}

func main() {
	task4()
}
