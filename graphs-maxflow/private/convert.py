from acmicpc import *

reader = Reader('uva820.in')
edges = [ ]
k = 1
n = 0
while reader.has_next():
  n = reader.read_int()
  s, t, m = reader.read_3_int()
  f = open('uva820_{0}.in'.format(k), 'w')
  f.write('{0} {1}\n'.format(n, m))
  f.write('{0} {1}\n'.format(s, t))
  for i in range(m):
    u, v, c = reader.read_3_int()
    f.write('{0} {1} {2}\n'.format(u, v, c))
  f.close()
  k += 1

k = 1
reader = Reader('uva820.ans')
while reader.has_next():
  flow =  reader.read_int()
  f = open('uva820_{0}.ans'.format(k), 'w')
  f.write(str(flow) + '\n')
  f.close()
  k += 1
