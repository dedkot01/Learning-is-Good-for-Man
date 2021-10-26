import time
from statistics import mean
import random

class Stub:
    def read():
        time.sleep(random.randint(1, 3))
        return 'read'

    def detect():
        time.sleep(random.randint(1, 3))
        return 'detect'

    def draw():
        time.sleep(random.randint(1, 3))
        return 'draw'

    def write():
        time.sleep(random.randint(1, 3))
        return 'write'

class Stopwatcher:
    def __init__(self, name='untiteled', count=3) -> None:
        self.times = []
        self.name = name
        self.count = count

    def timeit(self, func):
        ts = time.time()
        result = func()
        self.times.append(time.time() - ts)
        
        print(f'{self.name}: {self.times[-1]:.{3}} ms')
        if len(self.times) == self.count:
            print(f'{self.name}: average for {self.count} times - {mean(self.times):.{3}} ms')
            self.times = []
        
        return result

s_iter   = Stopwatcher(name='iteration')
s_read   = Stopwatcher(name='read')
s_detect = Stopwatcher(name='detect')
s_draw   = Stopwatcher(name='draw')
s_write  = Stopwatcher(name='write')

def iter():
    data = s_read.timeit(Stub.read)
    data = s_detect.timeit(Stub.detect)
    data = s_draw.timeit(Stub.draw)
    data = s_write.timeit(Stub.write)

while True:
    s_iter.timeit(iter)