from ipywidgets import IntProgress, GridspecLayout
from IPython.display import display
import time


class MyForm:
    def __init__(self) -> None:
        self.prgBar = IntProgress(min = 0, max = 100) # Создаем прогрессбар

    def show(self):
        display(self.prgBar) # Выводим прогрессбар на экран

        while self.prgBar.value < self.prgBar.max:   # Пока положение не дошло до максимума - продолжаем цикл
            self.prgBar.value = self.prgBar.value + 1 # Двигаем "полоску"
            time.sleep(0.1)
            
        print('Процесс завершен')
