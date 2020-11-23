import filecmp
from difflib import Differ

def diff_check(input, output, given):
  return filecmp.cmp(output, given), ''

"""
Always gives wrong answer
"""
def wa_check(input, output, given):
  return False, 'Wrong answer'

"""
ROTATION
"""
def rotation_check(input,ansRef,ansStud):
  try:
    ref = open(ansRef, 'r')
    stud = open(ansStud, 'r')
    refLines = [line.strip() for line in ref.readlines()]
    studLines = [line.strip() for line in stud.readlines()]
    if len(refLines) != len(studLines):
      return False, 'wrong number of lines in the output'
    
    for i in range(len(refLines)):
      refData = refLines[i].split(' ')
      studData = studLines[i].split(' ')
      if(len(studData) != 2):
        return False, 'wrong number of elements in a line'
      for j in range(len(refData)):
        if float(refData[j]) - float(studData[j]) > 1e-6:
          return False, 'wrong answer'
    return True, 'all good'
    
  except Exception as e:
    return False, 'there is a problem with your output format'

