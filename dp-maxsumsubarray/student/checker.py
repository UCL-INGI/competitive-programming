import filecmp

def diff_check(expected, given):
  return filecmp.cmp(expected, given), 'wrong answer'
