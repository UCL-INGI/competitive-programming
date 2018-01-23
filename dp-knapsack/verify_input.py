import os

def in_range(value, range, verbose = True):
  return range[0] <= value and value <= range[1]

def my_assert(assertion, msg):
  if not assertion:
    print('\t{0}'.format(msg))
  global ok
  ok = ok and assertion

def verify_knapsack(path = './tests/', CAP_RANGE = (1, 2000), N_RANGE = (1, 2000), W_RANGE = (1, 2000), V_RANGE = (1, 2000), verbose = True):
  for fn in os.listdir(path):
    if fn.endswith('.in'):
      print(fn)
      f = open(path + fn, 'r')
      lines = [line.strip() for line in f.readlines()]
      data = lines[0].split(' ')
      my_assert(len(data) == 2, 'line size not 2')
      cap = int(data[0])
      n = int(data[1])
      my_assert(in_range(cap, CAP_RANGE), 'cap={0} not in range'.format(cap))
      my_assert(in_range(n, N_RANGE), 'n={0} not in range'.format(n))
      my_assert(len(lines) == n + 1, 'wrong number of lines')
      w_sum = 0
      v_sum = 0
      for i in range(1, n + 1):
        data = lines[i].split(' ')
        my_assert(len(data) == 2, 'line size not 2')
        w = int(data[0])
        w_sum += w
        v = int(data[1])
        v_sum += v
        my_assert(in_range(w, W_RANGE), 'w={0} not in range'.format(w))
        my_assert(in_range(v, V_RANGE), 'v={0} not in range'.format(v))
      if(verbose): print('w_sum={0}, v_sum={1}'.format(w_sum, v_sum))
  if(ok): print('all ok')

if __name__ == '__main__':
  global ok
  ok = True
  verify_knapsack()
