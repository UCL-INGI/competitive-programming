import filecmp

def diff_check(input, output, given):
  return filecmp.cmp(output, given)