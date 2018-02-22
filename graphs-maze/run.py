import sys
import os
import checkers
import time
import math
from run_tools import *

"""
CONFIG
"""
taskname = 'file'
solname = 'yunoacsol'
timelimit = 2
checker = checkers.check
"""
END OF CONFIG
"""

filename = None
local = False
if sys.argv[1] == '1':
  local = True
  filename = sys.argv[2]

if not local:
  from inginious import feedback
  judging  = judge_java('yunoacsol', checker, 10000, './tests', False)
  max_time = judging.get_max_runtime()
  if max_time > timelimit:
    timelimit += max_time - timelimit

name = None
ext = None
files_before = set()

if local:
  for fn in os.listdir('./'):
    files_before.add(fn)
  os.system('cp ./private/{0} ./{0}'.format(filename))
else:
  os.system('getinput {0}:filename > tmp'.format(taskname))
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
    os.system('getinput {0} > {1}.java'.format(taskname, name))
    clear_package('{0}.java'.format(name))
  else:
    clear_package(filename)
  judging = judge_java(name, checker, timelimit)
  print('finished judging java')
elif(ext == 'cpp'):
  print('received cpp solution')
  if not local:
    os.system('getinput {0} > {1}.cpp'.format(taskname, name))
    clear_package()
  judging = judge_cpp(name, checker, timelimit)
  print('finished judging cpp')
elif(ext == 'py'):
  print('received python solution')
  if not local:
    os.system('/bin/bash -c "getinput {0} > {1}.py"'.format(taskname, name))
  judging = judge_py(name, checker, timelimit)
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
