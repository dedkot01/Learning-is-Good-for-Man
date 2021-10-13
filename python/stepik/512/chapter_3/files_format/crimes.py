import csv
import time
import collections

crimes_on_2015 = []

with open('Crimes.csv') as f:
    reader = csv.DictReader(f)
    for row in reader:
        if time.strptime(row['Date'], '%m/%d/%Y %H:%M:%S %p').tm_year == 2015:
            crimes_on_2015.append(row['Primary Type'])

counter = collections.Counter(crimes_on_2015)
print(counter.most_common(3))