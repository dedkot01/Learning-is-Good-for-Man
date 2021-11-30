def binary_search(list, item):
    low = 0
    high = len(list) - 1

    while low <= high:
        mid = (low + high)
        guess = list[mid]
        if guess == item:
            return mid
        if guess > item:
            high = mid - 1
        else:
            low = mid + 1

    return None


if __name__ == '__main__':
    l1 = [x for x in range(1, 10, 2)]
    print(l1)
    print(binary_search(l1, 1))
    print(binary_search(l1, 2))
