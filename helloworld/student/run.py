import os
from inginious import feedback
import checker
import time
import math

"""
CONFIG
"""
verbose = True
classname = 'Main'
taskname = 'file'
solname = 'yunoacsol'
"""
END OF CONFIG
"""

def is_empty_file(fn):
  return os.path.getsize(fn) == 0

def readfile(filename):
  f = open(filename, 'r')
  lines = [line.strip() for line in f.readlines()]
  return '\n'.join(lines)

def compile_java(classname):
  os.system('> err')
  os.system('javac {0}.java 2> err'.format(classname))
  if not is_empty_file('err'):
    return False, readfile(err)
  return True, ''


def run_tests_java():
  student_times = [ ]
  student_output_verdict = [ ]
  student_output_feedback = [ ]
  my_times = [ ]
  # loop over the input files
  for fn in os.listdir('./tests/'):
    if fn.endswith('.in'):
      # input file found
      if verbose: print('running: {0}'.format(fn))
      # get the test case name
      name = fn.split('.')[0]
      # get the cpu start time for the student solution
      start_time = time.clock()
      # run student java solution
      if verbose: print('running student solution')
      os.system('cat ./tests/{0} | java {1} > output'.format(fn, classname))
      # cumpute the runtime
      student_cpu_time = time.clock() - start_time
      if verbose: print('student solution finished in: {0}s'.format(student_cpu_time))
      student_times.append(student_cpu_time)
      # get the cpu start time for the my solution
      start_time = time.clock()
      # run my java solution
      if verbose: print('running my solution')
      os.system('cat ./tests/{0} | java {1}'.format(fn, solname))
      my_cpu_time = time.clock() - start_time
      if verbose: print('my solution finished in: {0}s'.format(my_cpu_time))
      my_times.append(my_cpu_time)
      # update the maxtime
      ok, message = checker.diff_check('./tests/{0}.ans'.format(name), 'output')
      # add verdict
      if ok:
        student_output_verdict.append(True)
        student_output_feedback.append('correct')
      else:
        student_output_verdict.append(False)
        student_output_feedback.append(message)
  return student_times, my_times, student_output_verdict, student_output_feedback

def output_feedback(student_times, my_times, verdict, feedback):
  s = ''
  WA_CNT = 0
  TLE_CNT = 0
  for i in range(len(student_times)):
    TLE = False
    WA = False
    s += '---test {0}---\n'.format(i)
    s += 'your runtime in seconds: {0}\n'.format(student_times[i])
    s += 'my runtime in seconds: {0}\n'.format(my_times[i])
    if 10 * student_times[i] > my_times[i]:
      s += 'your code is more than 10 times slower on this test.\n'.format(i)
      TLE = True
    if not verdict[i]:
      s += 'you code procudes a wrong answer for this test.\n'
      s += verdict[i] + '\n'
      WA = True
    s += 'verdict: '
    if WA and TLE:
      WA_CNT += 1
      TLE_CNT += 1
      s += '[WA] [TLE]\n'
    elif WA:
        WA_CNT += 1
        s += '[WA]\n'
    elif TLE:
        TLE_CNT += 1
        s += '[TLE]\n'
    else:
        s += '[OK]\n'
    s += '------\n'
  if WA_CNT + TLE_CNT == 0:
    s += 'verdict: [ACCEPTED]'
  else:
    s += 'verdict: [WA: {0}] [TLE: {1}]'.format(WA_CNT, TLE_CNT)
  if WA_CNT + TLE_CNT == 0:
    feedback.set_global_result("success")
    feedback.set_global_feedback(s)
  else:
    feedback.set_global_result("failure")
    feedback.set_global_feedback(s)
  
if verbose: print('compiling my solution')

ok, msg = compile_java(solname)
if not ok:
  if(verbose): print('compilation error on my solution:')
  if(verbose): print(msg)
  os.system('feedback --result failed --feedback "{0}\n{1}"'.format('ups, it seems there is a problem. contact administrator.', message))  
  exit(0)

os.system('getinput {0} > Main.java'.format(taskname))

ok, msg = compile_java('Main')

if not ok:
  if(verbose): print('compilation error:')
  if(verbose): print(msg)
  os.system('feedback --result failed --feedback "{0}\n{1}"'.format('compilation error', msg))
  exit(0)

student_times, my_times, verdict, feedback = run_tests_java()
output_feedback(student_times, my_times, verdict, feedback)