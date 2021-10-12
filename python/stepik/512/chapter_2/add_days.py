from datetime import date, timedelta

year, month, day = map(int, input().split())
days = int(input())

d = date(year, month, day)
delta = timedelta(days = days)
d += delta

print(d.strftime("%Y %m %d"))