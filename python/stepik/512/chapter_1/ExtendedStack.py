class ExtendedStack(list):
    def sum(self):
        # операция сложения
        a = self.pop()
        b = self.pop()
        self.append(a + b)

    def sub(self):
        # операция вычитания
        a = self.pop()
        b = self.pop()
        self.append(a - b)

    def mul(self):
        # операция умножения
        a = self.pop()
        b = self.pop()
        self.append(a * b)

    def div(self):
        # операция целочисленного 
        a = self.pop()
        b = self.pop()
        self.append(a // b)