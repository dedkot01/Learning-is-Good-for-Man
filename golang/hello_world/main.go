/*
	My first try with Golang
*/

package main

import "fmt"

// main point
func main() {
	fmt.Println("Hello, World!")

	var txt = "Hello, User!"
	fmt.Println(txt)

	var x int
	fmt.Println(x)

	x = 10
	fmt.Println(x)

	name := "Eva00"
	fmt.Println(name)

	var (
		name_1 = "Eva01"
		age int = 25
	)
	fmt.Println(name_1)
	fmt.Println(age)

	fmt.Println(100 - 9)

	var feel string
	fmt.Println("What do u feel?")
	fmt.Scan(&feel)
	fmt.Println(feel)
}
