import random

def write(n):
  f = open('tests/{0}.in'.format(n), 'w')
  f.write(str(n) + '\n')
  f.close()


for i in range(50):
  n = random.randint(1, 1 << 31 - 1)
  write(n)
