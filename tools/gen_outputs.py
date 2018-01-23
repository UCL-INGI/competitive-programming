import sys
import os
classname = sys.argv[1]

os.system('javac {0}.java'.format(classname))
for fn in os.listdir('./tests/'):
  print(fn)
  if fn.endswith('.in'):
    name = fn.split('.')[0]
    os.system('cat ./tests/{0} | java {1} > ./tests/{2}'.format(fn, classname, name + '.ans'))
