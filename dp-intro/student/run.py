import os
from inginious import feedback
import checker
import time

taskname = 'dp1-maxarraysum-1'
time_limit = 2

def is_empty_file(fn):
  return os.path.getsize(fn) == 0


os.system('getinput {0} > Main.java'.format(taskname))

classname = 'Main'
os.system('javac {0}.java 2> err'.format(classname))

if not is_empty_file('err'):
  os.system('feedback --result failed --feedback {0}'.format('compilation error'))
  exit(0)

WA = 0
TLE = 0

for fn in os.listdir('./tests/'):
  if fn.endswith('.in'):
    print('running: {0}'.format(fn))
    name = fn.split('.')[0]
    start_time = time.time()
    os.system('run_student --time {0} cat ./tests/{1} | java {2} > output'.format(time_limit, fn, classname))
    run_time = time.time() - start_time
    if run_time > time_limit:
      TLE += 1
    else:
      ok, message = checker.diff_check('./tests/{0}.ans'.format(name), 'output')
      if not ok:
        WA += 1

if WA + TLE == 0:
  os.system('feedback --result success --feedback "{0}"'.format('correct'))
else:
  os.system('feedback --result failed --feedback "time limit exceed in {0} cases. wrong answer in {1} cases."'.format(TLE, WA))
