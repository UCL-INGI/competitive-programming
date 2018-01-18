import filecmp
from difflib import Differ

def diff_check(input, output, given):
  d = Differ()
  result = list(d.compare(output, given))
  for line in result:
    print(line)
  return filecmp.cmp(output, given)