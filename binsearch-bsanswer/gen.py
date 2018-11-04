import random

def gen_random(n):
  pos = set()
  while len(pos) < n:
    x = random.randint(0, 10001)
    y = random.randint(0, 10001)

    a.append(random.randint(0, 50))
  f = open('tests/rnd_{0}_{1}.in'.format(n, q), 'w')
  f.write('{0} {1}\n'.format(len(a), q))
  for i in range(len(a)):
    f.write(str(a[i]))
    if i < len(a) - 1:
      f.write(' ')
  f.write('\n')
  for i in range(q):
    f.write(str(random.randint(0, 50)))
    f.write('\n')
  f.close()  

gen_random(100000, 10000)
gen_random(100000, 1000)


