def diff_check(input, output, given):
  f = open(output, 'r')
  lines1 = [ line.strip() for line in f.readlines() ]
  f = open(given, 'r')
  lines2 = [ line.strip() for line in f.readlines() ]
  if len(lines1) != len(lines2):
    return False, ''
  for i in range(len(lines1)):
    if lines1[i] != lines2[i]:
      return False, ''
  return True, ''
