import os
from inginious import feedback
import checkers
import time
import math
from run_tools import *

"""
CONFIG
"""
classname = 'Main'
taskname = 'file'
solname = 'yunoacsol'
timelimit = 2
checker = checkers.diff_check
"""
END OF CONFIG
"""

judging  = judge_java('yunoacsol', './tests', checker, 10000, False)
max_time = judging.get_max_runtime()
print(max_time)
TL = max(timelimit, 3 * max_time)
print(TL)

os.system('getinput feedback > tmp')
f = open('tmp', 'r')
print(f.readlines())

os.system('getinput {0}:filename > tmp'.format(taskname))
f = open('tmp', 'r')
filename = f.readlines()[0].strip()
name = filename.split('.')[0]
ext = filename.split('.')[1]
if(ext == 'java'):
    print('received java solution')
    print(filename)
    os.system('getinput {0} > {1}.java'.format(taskname, name))
    judging = judge_java(filename, './tests', checker, TL)
    print('finished judging java')
elif(ext == 'cpp'):
    print('received cpp solution')
    os.system('getinput {0} > {1}.cpp'.format(taskname, name))
    judging = judge_cpp(filename, './tests', checker, TL)
    print('finished judging cpp')

if judging.is_accepted():
 feedback.set_global_result("success")
else:
  feedback.set_global_result("failure")
feedback.set_global_feedback(judging.produce_feedback_message())