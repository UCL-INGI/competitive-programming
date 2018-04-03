import os

for fn in os.listdir('./'):
  if fn.endswith('.ans'):
    f = open(fn, 'r')
    lines = [ line.strip() for line in f.readlines() ]
    f.close()
    f = open(fn, 'w')
    if lines[0] == 'no':
      #print('changed ' + fn)
      f.write('yes\n')
    else:
      f.write('no\n')
    f.close()
      

