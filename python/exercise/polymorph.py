class Animal:
    def __init__(self, name):
        self.name = name


class Cat(Animal):
    def __init__(self, name):
        super().__init__(name)

    def meow(self):
        print(f'{self.name} - meow')


class Dog(Animal):
    def __init__(self, name):
        super().__init__(name)

    def woof(self):
        print(f'{self.name} - woof')


class Human:
    def __init__(self, name):
        self.name = name

    def do_something(self):
        print(f'{self.name} do something')


class Student(Human):
    def __init__(self, name):
        super().__init__(name)

    def do_homework(self):
        print(f'{self.name} do homework')


class Teacher(Human):
    def __init__(self, name):
        super().__init__(name)

    def teaching(self):
        print(f'{self.name} teaching')


def print_human_names(l: list[Human]) -> None:
    for i in l:
        print(i.name)


def print_animal_names(l: list[Animal]) -> None:
    for i in l:
        print(i.name)


if __name__ == '__main__':
    l_human: list[Human] = [Student('Dima'), Teacher('Tatiana')]
    l_animal: list[Animal] = [Dog('Sharik'), Cat('Murzik')]

    print(l_animal)
    print(l_human)

    print_human_names(l_human)
    print('-----------')
    print_animal_names(l_animal)

    print('===========')

    print_human_names(l_animal)
    print('-----------')
    print_animal_names(l_human)
