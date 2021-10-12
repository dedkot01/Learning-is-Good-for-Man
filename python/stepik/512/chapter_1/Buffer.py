class Buffer:
    def __init__(self):
        self.acc = []
        
    def add(self, *a):
        for i in a:
            self.acc.append(i)
            if len(self.acc) == 5:
                print(sum(self.acc))
                self.acc = []
    
    def get_current_part(self):
        return self.acc

buf = Buffer()
buf.add(1, 2, 3)
buf.get_current_part() # вернуть [1, 2, 3]
buf.add(4, 5, 6) # print(15) – вывод суммы первой пятерки элементов
buf.get_current_part() # вернуть [6]
buf.add(7, 8, 9, 10) # print(40) – вывод суммы второй пятерки элементов
buf.get_current_part() # вернуть []
buf.add(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1) # print(5), print(5) – вывод сумм третьей и четвертой пятерки
buf.get_current_part() # вернуть [1]