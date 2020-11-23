import filecmp
from difflib import Differ

def diff_check(input, output, given):
  return filecmp.cmp(output, given), ''
