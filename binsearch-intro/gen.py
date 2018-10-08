import random

def gen_range_repreat(min, max, rep):
  a = [ ]
  for i in range(min, max + 1):
    for k in range(rep):
      a.append(i)
  f = open('tests/range_rep_{0}_{1}_{2}.in'.format(min, max, rep), 'w')
  f.write('{0} {1}\n'.format(len(a), len(a) // rep))
  for i in range(len(a)):
    f.write(str(a[i]))
    if i < len(a) - 1:
      f.write(' ')
  f.write('\n')
  for i in range(min, max + 1):
    f.write(str(i))
    f.write('\n')
  f.close()

def gen_range_even(min, max):
  a = [ ]
  for i in range(min, max + 1):
    if i % 2 == 0:
      a.append(i)
  f = open('tests/range_even_{0}_{1}.in'.format(min, max), 'w')
  f.write('{0} {1}\n'.format(len(a), max - min + 1))
  for i in range(len(a)):
    f.write(str(a[i]))
    if i < len(a) - 1:
      f.write(' ')
  f.write('\n')
  for i in range(min, max + 1):
    f.write(str(i))
    f.write('\n')
  f.close()

def gen_random(n, q):
  a = [ ]
  for i in range(n):
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

gen_random(10, 100)
