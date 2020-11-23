import sys
import os
import time
import math
from run_tools import *
from config import *

filename = None
local = False
if sys.argv[1] == '1':
  if READ:
    print('read')
    exit(0)
  else:
    print('run')
  local = True
  filename = sys.argv[2]

if not local:
  from inginious import feedback
  if READ:
    feedback.set_global_result("success")
    exit(0)
  judging  = judge_java(SOLNAME, CHECKER, 10000, './tests', False)
  max_time = judging.get_max_runtime()
  if max_time > TIMELIMIT:
    TIMELIMIT += max_time - TIMELIMIT


name = None
ext = None
files_before = set()

if local:
  for fn in os.listdir('./'):
    files_before.add(fn)
  os.system('cp ./private/{0} ./{0}'.format(filename))
else:
  os.system('getinput {0}:filename > tmp'.format(TASKNAME))
  f = open('tmp', 'r')
  filename = f.readlines()[0].strip()

ext = filename.split('.')[1]
name = filename.split('.')[0]

def clear_package(filename):
  f = open('{0}.java'.format(name), 'r')
  lines = [ line.strip() for line in f.readlines() ]
  filtered = [ ]
  for line in lines:
    if not line.startswith('package'):
      filtered.append(line)
  f.close()
  f = open('{0}.java'.format(name), 'w')
  for line in filtered:
    f.write(line + '\n')
  f.close()

if(ext == 'java'):
  print('received java solution')
  if not local:
    os.system('getinput {0} > {1}.java'.format(TASKNAME, name))
    clear_package('{0}.java'.format(name))
  else:
    clear_package(filename)
  judging = judge_java(name, CHECKER, TIMELIMIT)
  print('finished judging java')
elif(ext == 'cpp'):
  print('received cpp solution')
  if not local:
    os.system('getinput {0} > {1}.cpp'.format(TASKNAME, name))
  judging = judge_cpp(name, CHECKER, TIMELIMIT)
  print('finished judging cpp')
elif(ext == 'py'):
  print('received python solution')
  if not local:
    os.system('/bin/bash -c "getinput {0} > {1}.py"'.format(TASKNAME, name))
  judging = judge_py(name, CHECKER, TIMELIMIT)
  print('finished judging python')
    

if local:
  for fn in os.listdir('./'):
    if not fn in files_before:
      print('deleting {0}'.format(fn))
      os.system('rm ./{0}'.format(fn))
  print(judging.produce_feedback_message())
else:
  if judging.is_accepted():
    feedback.set_global_result("success")
  else:
    feedback.set_global_result("failed")
  feedback.set_global_feedback(judging.produce_feedback_message())

