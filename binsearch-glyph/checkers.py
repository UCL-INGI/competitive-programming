import os
import math

def icpc(input_fn, output_fn, output_team):
  os.system('./a.out {0} {1} . < {2} ; echo $? > code.txt'.format(input_fn, output_fn, output_team))
  f = open('code.txt', 'r')
  lines = [line.strip() for line in f.readlines()]
  if lines[0] == '42':
    return True, 'AC'
  return False, 'WA'

def score(k, x, y):
  n = len(x)
  sector = 2 * math.pi / k
  rmin = 1e100
  rmax = 0
  for i in range(n):
    alpha = math.atan2(y[i], x[i])
    if alpha < 0:
      alpha += 2 * math.pi
    while alpha > sector:
      alpha -= sector
    r = math.hypot(x[i], y[i]) * math.cos(alpha - sector / 2)
    rmin = min(rmin, r)
    rmax = max(rmax, r)
  return (rmin * rmin) / (rmax * rmax)

def glyph(input_fn, output_fn, output_team):
  f = open(input_fn, 'r')
  lines = [ line.strip() for line in f.readlines() ]
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
