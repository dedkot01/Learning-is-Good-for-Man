import datetime

x = {'timestamp': 1568317626000}
print(x['timestamp'])
my_date = datetime.date.fromtimestamp(x['timestamp'] // 1000)
print(my_date)
