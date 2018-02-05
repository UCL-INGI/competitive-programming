import sys
import os
import time
classname = sys.argv[1]

os.system('javac {0}.java'.format(classname))
max_time = 0
for fn in os.listdir('./tests/'):
  print(fn)
  if fn.endswith('.in'):
    name = fn.split('.')[0]
    start = time.time()
    os.system('cat ./tests/{0} | java {1} > ./tests/{2}'.format(fn, classname, name + '.ans'))
    end = time.time()
    elapsed = end - start
    max_time = max(elapsed, max_time)

print('max time: {0}'.format(max_time))
