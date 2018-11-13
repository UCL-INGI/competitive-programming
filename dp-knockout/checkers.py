import os
import math

eps = 1e-6

def float(input_fn, output_fn, output_team):
  f = open(output_fn, 'r')
  lines = [ line.strip() for line in f.readlines() ]
  f.close()
  answer = float(lines[0])
  try:
    f = open(output_team, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    f.close()
    if len(lines) != 1:
      return False, 'Wrong number of lines'
    team_answer = float(lines[0])
    delta = abs(answer - team_answer)
    print(delta)
    if abs(answer - team_answer) > eps:
      return False, 'Wrong answer'
    return True, 'all ok'
  except:
    return False, 'Wrong  output format'  

