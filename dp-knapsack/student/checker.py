import filecmp

def get_lines(file):
  f = open(file, 'r')
  lines = [line.strip() for line in f.readlines()]
  f.close()
  return lines

def diff_check(input, expected, given):
  return filecmp.cmp(expected, given), 'wrong answer'

"""
Input format:

first line: capacity nb-items
one line per item with: value weight

Output format:

first line: solution-value solution-weight
second line: binary string with bi = 1 iff solution takes item i
"""
def knapsack_check(input, expected, given):
  # read input
  input_lines = get_lines(input)
  data = input_lines[0].split(' ')
  capacity = int(data[0])
  nb_items = int(data[1])
  values = [ ]
  weights = [ ]
  for i in range(nb_items):
    data = input_lines[i + 1].split(' ')
    values.append(int(data[0])
    weights.append(int(data[1])
  # read given
  try:
    given_lines = get_lines(given)
    data = given_lines[0].split(' ')
    total_value = int(data[0])
    total_weight = int(data[1])
    taken = given_lines[1]
    if len(taken) != nb_items:
      return False, 'expected binary string of length {0} but found {1}'.format(nb_items, len(taken))    
    taken_value = 0
    taken_weight = 0
    for i in range(nb_items):
      if taken[i] == '1':
        taken_value += values[i]
        taken_weight += weights[i]
      elif taken[i] != '0':
        return False, 'expected binary string but character {0} found'.format(taken[i])
     if taken_value != total_value:
        return False, 'said total value is {0} but the taken items have total value {1}'.format(total_value, taken_value)
     if taken_weight != total_value:
        return False, 'said total value is {0} but the taken items have total value {1}'.format(total_value, taken_value)

  except:
    return False, 'problem with output format'
