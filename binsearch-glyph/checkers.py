import os

def icpc(input_fn, output_fn, output_team):
  os.system('./a.out {0} {1} . < {2} ; echo $? > code.txt'.format(input_fn, output_fn, output_team))
  f = open('code.txt', 'r')
  lines = [line.strip() for line in f.readlines()]
  if lines[0] == '42':
    return True, 'AC'
  return False, 'WA'

