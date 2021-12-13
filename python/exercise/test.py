#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def timeConversion(s):
    # Write your code here
    m = s[-2:]
    if m == 'PM':
        return f'{(int(s[:2]) + 12) % 24}:{s[3:-2]}'
    else:
        return f'{s[:-2]}'

if __name__ == '__main__':

    s = input()

    result = timeConversion(s)
    print(result)
