import random

def write(n):
  f = open('tests/{0}.in'.format(n), 'w')
  f.write(str(n) + '\n')
  f.close()


for i in range(50):
  n = random.randint(1, 1 << 31)
  write(n)

write(1 << 31 - 1)

for i in range(25):
  n = random.randint(1, 1 << 15)
  write(n * n)

write(1)
