import time

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

# while True:
#     data = {}
#     data = frame_reader.read()
#     data = detector.detect()
#     data = drawer.draw()
#     data = frame_writer.write()