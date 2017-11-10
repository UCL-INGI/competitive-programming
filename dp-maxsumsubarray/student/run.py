import os
from inginious import feedback
import checker
import time
import math

taskname = 'dp1-maxarraysum-1'
time_limit = 5

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

max_time = 0

for fn in os.listdir('./tests/'):
  if fn.endswith('.in'):
    print('running: {0}'.format(fn))
    name = fn.split('.')[0]
    start_time = time.time()
    os.system('run_student --time {0} cat ./tests/{1} | java {2} > output'.format(time_limit, fn, classname))
    run_time = time.time() - start_time
    max_time = max(max_time, run_time)
    if run_time > time_limit:
      TLE += 1
    else:
      ok, message = checker.diff_check('./tests/{0}.ans'.format(name), 'output')
      if not ok:
        WA += 1

if WA + TLE == 0:
  os.system('feedback --result success --feedback "correct. max runtime is {0:.3f}"'.format(max_time))
else:
  os.system('feedback --result failed --feedback "wrong answer in {0} cases. time limit exceed in {1} cases. max runtime is {2:.3f}."'.format(WA, TLE, max_time))
