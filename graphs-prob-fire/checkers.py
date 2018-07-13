import filecmp
from difflib import Differ

def diff_check(input, output, given):
  """
  print('-----')
  f = open(output, 'r')
  print([l for l in f.readlines()])
  f = open(given, 'r')
  print([l for l in f.readlines()])
  print('-----')
  """
  return filecmp.cmp(output, given), ''

"""
Always gives wrong answer
"""
def wa_check(input, output, given):
  return False, 'Wrong answer'

"""
KNAPSACK
"""
def read_knapsack_input(filename):
  f = open(filename, 'r')
  lines = [line.strip() for line in f.readlines()]
  data = lines[0].split(' ')
  C = int(data[0])
  n = int(data[1])
  w = [ ]
  v = [ ]
  for i in range(1, n + 1):
    data = lines[i].split(' ')
    w.append(int(data[0]))
    v.append(int(data[1]))
  return C, n, w, v

def read_knapsack_output(filename):
  f = open(filename, 'r')
  lines = [line.strip() for line in f.readlines()]
  value = int(lines[0])
  items = [int(x) for x in lines[1].split(' ')]
  return value, items

def knapsack_check(input, output, given):
  try:
    C, n, w, v = read_knapsack_input(input)
    value_mine, items_mine = read_knapsack_output(output)
    value_other, items_other = read_knapsack_output(given)
    item_set = set()
    for i in items_other:
      if i < 0 or i >= n:
        return False, 'item index out of bounds. index={0} not in [{1}, {2}]'.format(i, 0, n - 1)
      item_set.add(i)
    if len(item_set) != len(items_other):
      return False, 'duplicated items in the knapsack'
    if value_mine != value_other:
      return False, 'wrong value. expected={0}, given={1}'.format(value_mine, value_other)
    weight_used = 0
    value_used = 0
    for i in items_other:
      weight_used += w[i]
      value_used += v[i]
    if weight_used > C:
      return False, 'capacity exceeded. capacity={0}, used={1}'.format(C, weight_used)
    if value_used != value_other:
      return False, 'item values does not match provided values. value={0}, item value={1}'.format(value_other, value_used)
    return True, 'all good'
  except:
    return False, 'there is a problem with your output format'
