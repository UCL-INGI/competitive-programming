import os
from inginious import feedback
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
checker = checkers.diff_check
"""
END OF CONFIG
"""

judging  = judge_java('yunoacsol', checker, 10000, './tests', False)
max_time = judging.get_max_runtime()

if max_time > timelimit:
    timelimit += max_time - timelimit

os.system('/bin/bash -c "getinput feedback > tmp"')
f = open('tmp', 'r')
print(f.readlines())

os.system('/bin/bash -c "getinput {0}:filename > tmp"'.format(taskname))
f = open('tmp', 'r')
filename = f.readlines()[0].strip()
name = filename.split('.')[0]
ext = filename.split('.')[1]
if(ext == 'java'):
  print('received java solution')
  os.system('/bin/bash -c "getinput {0} > ./student/{1}.java"'.format(taskname, name))
  judging = judge_java(name, checker, timelimit)
  print('finished judging java')
elif(ext == 'cpp'):
  print('received cpp solution')
  os.system('/bin/bash -c "getinput {0} > ./student/{1}.cpp"'.format(taskname, name))
  judging = judge_cpp(name, checker, timelimit)
  print('finished judging cpp')
elif(ext == 'py'):
  print('received python solution')
  os.system('/bin/bash -c "getinput {0} > ./student/{1}.py"'.format(taskname, name))
  judging = judge_py(name, checker, timelimit)
  print('finished judging python')
    

if judging.is_accepted():
 feedback.set_global_result("success")
else:
  feedback.set_global_result("failed")
feedback.set_global_feedback(judging.produce_feedback_message())