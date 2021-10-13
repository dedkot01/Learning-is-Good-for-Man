import requests
import json

with open('numbers.txt') as n, \
        open('answer.txt', 'w') as w:
    for number in n:
        res = requests.get('http://numbersapi.com/{}/math?json=true'.format(number.strip()))
        j = json.loads(res.text)
        if j['found']: w.write('Interesting\n')
        else: w.write('Boring\n')