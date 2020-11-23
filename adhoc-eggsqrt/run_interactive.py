import sys
import os
from config import *
import time
import subprocess

local = len(sys.argv) > 1

# import feedback if we are on inginious
if not local: from inginious import feedback

if local:
  filename = sys.argv[1]
  #for fn in os.listdir('./'):
  #  files_before.add(fn)
  #os.system('cp ./private/{0} ./{0}'.format(filename))
else:
  os.system('getinput {0}:filename > tmp'.format(TASKNAME))
  f = open('tmp', 'r')
  filename = f.readlines()[0].strip()

name = filename.split('.')[0]
ext = filename.split('.')[1]

# function to clear the package on java submissions
def clear_package(name, ext):
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

def is_empty_file(fn):
  return os.path.getsize(fn) == 0

def readlines(fn):
  f = open(fn, 'r')
  lines = [line.strip() for line in f.readlines()]
  return lines

def compile_code(name, ext):
  if ext == 'java': # compile java
    clear_package(name, ext)
    os.system('> err')
    os.system('javac {0}.java 2> err'.format(name))
    return is_empty_file('err'), readlines('err')
  elif ext == 'cpp': # compile cpp
    os.system('> err')
    os.system('g++ -w -O2 -std=c++11 -o {0} {1}.cpp 2> err'.format(name, name))
    return is_empty_file('err'), readlines('err')
  elif ext == 'py': # compile python
    return True, ''
  return False, 'only .java .cpp and .py are accepted'

def close():
  os.system('rm *.pyc')
  os.system('rm *.class')
  os.system('rm err')
  os.system('rm verdict')
  os.system('rm -r __pycache__')
  exit(0)

def CE(msg):
  if local:
    print(msg)
    print('compile error')
    close()
  else:
    feedback.set_global_result('failed')
    feedback.set_global_feedback('compile error')
    close()

def WA(msg):
  if local:
    print(msg)
    print('wrong answer')
    close()
  else:
    feedback.set_global_result('failed')
    feedback.set_global_feedback('wrong answer')
    close()

def TLE(msg):
  if local:
    print(msg)
    print('time limit exceeded')
    close()
  else:
    feedback.set_global_result('failed')
    feedback.set_global_feedback('time limit exceeded')
    close()

def AC():
  if local:
    print('accepted')
    close()
  else:
    feedback.set_global_result("success")
    feedback.set_global_feedback('accepted')
    close()

# compile the code
ok, msg = compile_code(name, ext)
if not ok: CE(msg)

# run the checker
for fn in os.listdir('./tests/'):
  if not fn.endswith('.in'): continue
  start_time = time.time()
  subprocess.run('python {0}.py {1} {2} {3}'.format(CHECKER, './tests/' + fn, name, ext), shell = True, timeout = TIMELIMIT)
  f = open('verdict', 'r')
  lines = [line.strip() for line in f.readlines()]
  if lines[0] == 'ko':
    WA(lines[1])
  end_time = time.time()
  run_time = end_time - start_time
  if run_time > TIMELIMIT: TLE(run_time)
AC()
