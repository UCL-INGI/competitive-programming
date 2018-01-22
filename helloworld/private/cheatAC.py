import os
import sys
import filecmp

f = open('in_data', 'w')
for line in sys.stdin:
  f.write(line.strip())
  f.write('\n')
f.close()  

def readlines(fn):
  f = open(fn, 'r')
  return [line.strip() for line in f.readlines()]

def compare(fn1, fn2):
  lines1 = readlines(fn1)
  lines2 = readlines(fn2)
  if len(lines1) == len(lines2):
    for i in range(len(lines1)):
      if lines1[i] != lines2[i]:
        return False
    return True
  return False

for fn in os.listdir('./tests'):
  if fn.endswith('.in'):
    if filecmp.cmp('./tests/' + fn, 'in_data'):
      name = fn.split('.')[0]
      f = open('./tests/' + name + '.ans', 'r')
      lines3 = [line.strip() for line in f.readlines()]
      print(lines3[0])
      break
