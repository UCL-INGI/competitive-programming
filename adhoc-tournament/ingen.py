import random
from IO import *

def gen_all(k):
  n = (1 << k)
  q = k * n
  if q > 100000:
    return False
  print('gen all')
  writer = Writer()
  writer.write_int(n)
  writer.write_int(q)
  for p in range(1, n + 1):
    for r in range(1, k + 1):
      writer.write_tuple((p, r))
  writer.write_file('tests/all_k={0}.in'.format(k))
  return True

def gen_rnd(k):
  print('gen rnd')
  n = (1 << k)
  q = 100000
  writer = Writer()
  writer.write_int(n)
  writer.write_int(q)
  Q = set()
  while len(Q) < q:
    p = random.randint(1, n)
    r = random.randint(1, k)
    Q.add( (p, r) )
  for p, r in Q:
    writer.write_tuple((p, r))
  writer.write_file('tests/rnd_k={0}.in'.format(k))
  return True


for k in range(1, 21):
  print(k)
  ok = gen_all(k)
  if not ok:
    gen_rnd(k)

