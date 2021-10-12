class MoneyBox:
    def __init__(self, capacity):
        self.capacity = capacity
        self.value = 0

    def can_add(self, v):
        if (self.value + v) <= self.capacity: return True
        else: return False

    def add(self, v):
        if self.can_add(v): self.value += v

b1 = MoneyBox(10)
b1.add(10)
b1.add(1)
print(b1.value)