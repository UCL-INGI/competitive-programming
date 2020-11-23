import os
import math

def one_double(input_fn, output_fn, output_team):
  try:
    f = open(output_fn, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    ans = float(lines[0])
    f = open(output_team, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    if len(lines) != 1:
      return False, 'Wrong number of lines'
    ans_team = float(lines[0])
    if abs(ans - ans_team) > 1e-6:
      return False, 'Wrong answer'
    return True, 'all ok'
  except:
    return False, 'Wrong  output format'
