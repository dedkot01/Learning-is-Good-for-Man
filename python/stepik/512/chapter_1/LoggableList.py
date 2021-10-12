import time

class Loggable:
    def log(self, msg):
        print(str(time.ctime()) + ": " + str(msg))

class LoggableList(list, Loggable):
    def append(self, v):
        super().append(v)
        self.log(v)

l = LoggableList().append(1)
print(l)