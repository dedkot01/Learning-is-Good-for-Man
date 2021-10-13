s = input()
t = input()

count = 0
for i in range(len(s) - len(t) + 1):
    if s[i::].startswith(t): count += 1

print(count)