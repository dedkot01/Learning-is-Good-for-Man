# https://stepik.org/lesson/17744/step/3?unit=4417

def dijkstra(v: list, s: int, g: dict):
    d = {}
    for e in v:
        d[e] = None
    d[s] = 0
    

kv, kw = map(int, input().split())
g = {}
for i in range(kw):
    s, e, w = map(int, input().split())
    g[(s, e)] = w
s, e = map(int, input().split())
