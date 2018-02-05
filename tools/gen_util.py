import os

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
