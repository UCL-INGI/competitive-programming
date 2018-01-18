import sys
import os
classname = sys.argv[1]

os.system('javac {0}.java'.format(classname))
for fn in os.listdir('./'):
  print(fn)
  if fn.endswith('.in'):
    name = fn.split('.')[0]
    os.system('cat {0} | java {1} > {2}'.format(fn, classname, name + '.ans'))
