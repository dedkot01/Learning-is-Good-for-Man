import time

from python.exercise.stub import Stub

def timeit(method):
    def timed(*args, **kw):
        ts = time.time()
        result = method(*args, **kw)
        te = time.time()
        if 'log_time' in kw:
            name = kw.get('log_name', method.__name__.upper())
            kw['log_time'][name] = int((te - ts) * 1000)
        else:
            print('{} - {} ms'.format(method.__name__, (te - ts) * 1000))
        return result
    return timed

@timeit
def test(a):
    time.sleep(1)
    return a + 2

a = test(3)

print(a)

def func():
    time.sleep(1)
    print("Nice")

func()

class A:
    t = {}

    def timeit(func):
        ts = time.time()
        result = func()
        A.t.append(time.time() - ts)
        print(f'{A.t[-1]} ms')
        return result

A.timeit(func)
print(A.t[-1])

# a = timeit(lambda : (a = 2,
#     print(a)))

while True:
    data = {}
    data = Stub.read()
    data = Stub.detect()
    data = Stub.draw()
    data = Stub.write()