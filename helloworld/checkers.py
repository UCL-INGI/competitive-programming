import filecmp
from difflib import Differ

def diff_check(input, output, given):
  f = open(output, 'r')
  lines1 = [line.strip() for line in f.readlines()]
  f = open(given, 'r')
  lines2 = [line.strip() for line in f.readlines()]
  print('expected:')
  for i in range(len(lines1)):
    print(lines1[i])
  print('-----')
  print('given:')
  for i in range(len(lines2)):
    print(lines2[i])
  return filecmp.cmp(output, given)