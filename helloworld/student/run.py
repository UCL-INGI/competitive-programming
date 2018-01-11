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

os.system('getinput {0}:filename > tmp'.format(taskname))
f = open('tmp', 'r')
print(f.readlines())

os.system('getinput {0} > Main.java'.format(taskname))
judging = judge_java('Main', './tests', checker, TL)
if judging.is_accepted():
 feedback.set_global_result("success")
else:
  feedback.set_global_result("failure")
feedback.set_global_feedback(judging.produce_feedback_message())