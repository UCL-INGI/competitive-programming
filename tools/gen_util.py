import os
import filecmp

def find_next_filename(filename):
  k = 1
  while os.path.exists('./tests/{0}_{1}.in'.format(filename, k)):
    k += 1
  return '{0}_{1}'.format(filename, k)

"""
Compute gcd.
"""
def gcd(x, y):
  if(y == 0): return x
  return gcd(y, x % y)


def in_range(x, lb, ub):
  return lb <= x and x <= ub

"""
classname1: the name of the first class to run
classname2: the name of the second class to run
rnd_gen: a function that generates a random case and writes it in a given filename
"""
def gen_rnd_until_diff(classname1, classname2, rnd_gen):
  os.system('javac {0}.java'.format(classname1))
  os.system('javac {0}.java'.format(classname2))
  count = 1
  while True:
    print('try {0}'.format(count))
    rnd_gen('rnd')
    os.system('cat rnd.in | java {0} > out1'.format(classname1))
    os.system('cat rnd.in | java {0} > out2'.format(classname1))
    if not filecmp.cmp('out1', 'out2'):
      break
    count += 1
  print('generated a test case where solutions differ')
