objects = [1, 2, 3, 1, 2, 5]

ans = 0
for i in range(len(objects)):
    is_uniq = True
    for j in range(i + 1, len(objects), 1):
        if objects[i] is objects[j]:
            is_uniq = False
            break
    if is_uniq:
        ans += 1

print(ans)