s = input()
a = input()
b = input()

count = 0
while count < 1000:
    if s.find(a) != -1:
        count += 1
        s = s.replace(a, b)
    else:
        break

if count < 1000: print(count)
else: print('Impossible')