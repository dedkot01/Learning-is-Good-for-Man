import itertools

def primes():
    x = 1
    while True:
        x += 1
        is_prime = True
        for i in range(2, x - 1, 1):
            if x % i == 0: 
                is_prime = False
                break
        if is_prime: yield x 

print(list(itertools.takewhile(lambda x : x <= 31, primes())))
# [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31]