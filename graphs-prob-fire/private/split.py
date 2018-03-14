import os
from acmicpc import *

os.system('rm -r ./split/')
os.system('mkdir ./split/')

maxn = 0
maxm = 0

for fn in os.listdir('./uva/'):
  if not fn.endswith('.py'):
    reader = Reader('./uva/' + fn)
    t = reader.read_int()
    for i in range(t):
      n, m = reader.read_two_int()
      maxn = max(maxn, n)
      maxm = max(maxm, m)
      maze = [ ]
      for j in range(n):
        maze.append(reader.read_line())
      f = open('./split/' + fn + str(i + 1) + '.in', 'w')
      f.write(str(n) + ' ' + str(m) + '\n')
      for x in maze:
        f.write(x + '\n')
      f.close()
      if i > 10: break

print(maxn)
print(maxm)
