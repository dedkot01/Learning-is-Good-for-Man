def closest_mod_5(x):
    res = x
    while res % 5 != 0: res += 1
    return res

print(closest_mod_5(6))