import random

A = 'abcdefghijklmnopqrstuvwxyz[]'

def gen_random(l):
  x = ''
  for i in range(l):
    k = random.randint(0, len(A)-1)
    x += A[k]
  f = open('tests/rnd_A_' + str(l) + '.in', 'w')
  f.write(x)
  f.close()

for l in [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 1000, 10000, 20000, 30000, 40000, 49999, 50000]:
  gen_random(l)
