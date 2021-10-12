class NonPositiveError(Exception):
    pass

class PositiveList(list):
    def append(self, v):
        if v > 0: super().append(v)
        else: raise NonPositiveError(v)

l = PositiveList()
l.append(-1)