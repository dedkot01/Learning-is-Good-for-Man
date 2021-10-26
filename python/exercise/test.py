def f():
    a = 1

try: 
    f()
    print(a)
except:
    print('not defined')

for i in range(10):
    result = i

print(result)

if True:
    print(__name__)
    test = 1
else:
    test1 = 2

print(test)
print(test1)