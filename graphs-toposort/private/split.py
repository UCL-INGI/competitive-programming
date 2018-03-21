from acmicpc import *

reader = Reader('tests')
while reader.has_next():
  writer = Writer('uva')
  n, m = reader.read_int_pair()
  e = set()
  for i in range(m):
    u, v = reader.read_int_pair()
    e.add((u - 1, v - 1))
  writer.write_pair(n, len(e))
  writer.write_tuples(e, False)
  writer.close()
