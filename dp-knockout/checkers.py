import os
import math

eps = 1e-6

def float(input_fn, output_fn, output_team):
  f = open(output_fn, 'r')
  lines = [ line.strip() for line in f.readlines() ]
  answer = float(lines[0])
  f = open(input_fn, 'r')
  f.close()
  n = int(lines[0])
  x = [ ]
  y = [ ]
  for i in range(n):
    data = lines[i + 1].split(' ')
    x.append(int(data[0]))
    y.append(int(data[1]))
  try:
    f = open(output_team, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    f.close()
    if len(lines) != 1:
      return False, 'Wrong number of lines'
    data = lines[0].split(' ')
    n = int(data[0])
    r = float(data[1])
    if not (3 <= n and n <= 8):
      return False, 'Wrong value of n: {0}'.format(n)
    rr = score(n, x, y)
    if abs(r - rr) > 1e-6:
      return False, 'The score {0} does not match the value n={1}'.format(r, n)
    f = open(output_fn, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    data = lines[0].split(' ')
    rr = float(data[1])
    if abs(r - rr) > 1e-6:
      return False, 'The score is not good enough'
    return True, 'all ok'
  except:
    return False, 'Wrong  output format'
