import time
import os
import checkers

"""
Checks whether the given file is empty.
"""
def is_empty_file(fn):
  return os.path.getsize(fn) == 0

"""
Creates a list containing all lines from a given file.
"""
def readlines(fn):
  f = open(fn, 'r')
  lines = [line.strip() for line in f.readlines()]
  return lines

"""
Given a list of strings, create a string containing
all those strings separated by newlines for INGInious
presentation.
"""
def format_for_output(lines):
  '\n\n'.join(lines)

"""
Compiles a java code and returns two values:

- A flag that is true iff the compilation was successful
- A list will the lines from the compilation error message.
  The list is empty iff the compilation was sucessful.
"""
def compile_java(mainclass):
  os.system('> err')
  os.system('javac {0}.java 2> err'.format(mainclass))
  return is_empty_file('err'), readlines('err')

"""
Runs a java code on a given input and writes the output in the given
output file.

Returns the cpu run-time of that code.
"""
def run_java(mainclass = 'Main', inputfile = 'input', outputfile = 'output'):
  os.system('> err')
  start_time = time.clock()
  os.system('cat {0} | java {1} > {2} 2> err'.format(inputfile, mainclass, outputfile))
  end_time = time.clock()
  run_time = end_time - start_time
  return is_empty_file('err'), run_time

def judge_java(mainclass = 'Main', testdir = './tests', checker = checkers.diff_check, timelimit = 1, verbose = True):
  judging = Judging()
  # compile the student solution
  if(verbose): print('compiling java')
  compile_ok, err = compile_java(mainclass)
  if not compile_ok:
    if(verbose): print('compilation error')
    # compile error
    judging.set_compile_error()
    return judging
  if(verbose): print('compilation successful')
  # no compile error, so we run
  test_index = -1
  for fn in os.listdir(testdir):
    if fn.endswith('.in'):
      if(verbose): print('running test {0}'.format(fn))
      test_index += 1
      # get the name of the test case
      name = fn.split('.')[0]
      run_ok, time = run_java(mainclass, testdir + '/' + fn)
      if(verbose): print('run finished: {0}s'.format(time))
      # set the runtime
      judging.add_time(test_index, time)
      if time > timelimit:
        if(verbose): print('time limit exceeded')
        # time limit exceeded
        judging.add_time_limit_exceeded(test_index)
      if not run_ok:
        if(verbose): print('runtime error')
        # runtime error
        judging.add_runtime_error(test_index)
      else:
        if(verbose): print('chicking the answer')
        # check whether the answer is correct
        answer_ok = checker(testdir + '/' + fn, testdir + '/' + name + '.ans', 'output')
        if not answer_ok:
          if(verbose): print('wrong answer')
          # wrong_answer
          judging.add_wrong_anser(test_index)
        else:
          if(verbose): print('correct')
          # correct
          judging.add_correct(test_index)
  if(verbose): print('finished judging')
  return judging

class Judging:

  def __init__(self):
    self.compile_error = False
    self.wrong_answer = set()
    self.time_limit_exceeded = set()
    self.runtime_error = set()
    self.correct = set()
    self.run_time = { }
  
  def set_compile_error(self, compile_error = True):
    self.compile_error = compile_error
  
  def add_wrong_anser(self, test_index):
    self.wrong_answer.add(test_index)
  
  def add_time_limit_exceeded(self, test_index):
    self.time_limit_exceeded.add(test_index)

  def add_runtime_error(self, test_index):
    self.runtime_error.add(test_index)

  def add_correct(self, test_index):
    self.correct.add(test_index)

  def add_time(self, test_index, run_time):
    self.run_time[test_index] = run_time

  def is_accepted(self):
    return not self.is_compile_error() and not self.is_wrong_answer() and not self.is_time_limit_exceeded() and not self.is_runtime_error()
  
  def is_wrong_answer(self):
    return len(self.wrong_answer) > 0

  def is_time_limit_exceeded(self):
    return len(self.time_limit_exceeded) > 0

  def is_runtime_error(self):
    return len(self.runtime_error) > 0

  def is_compile_error(self):
    return self.compile_error

  def produce_feedback_message(self):
    if self.is_compile_error():
      return 'Compile error\n\n'.format()
    s = ''
    for test_index in self.run_time:
      status = ''
      if test_index in self.correct:
        status = '[CORRECT]'
      if test_index in self.wrong_answer:
        status += '[WA]'
      if test_index in self.time_limit_exceeded:
        status += '[TLE]'
      if test_index in self.runtime_error:
        status += '[RE]'
      s += 'Test #{0}: runtime={1:.3f}, status={2}\n\n'.format(test_index + 1, self.run_time[test_index], status)
    overall_status = 'verdict: '
    if self.is_accepted():
      overall_status = '[ACCEPTED]'
    if self.is_wrong_answer():
      overall_status += '[WRONG ANSWER]'
    if self.is_time_limit_exceeded():
      overall_status += '[TIME LIMIT EXCEEDED]'
    if self.is_runtime_error():
      overall_status += '[RUNTIME ERROR]'
    s += overall_status
    return s