import random
import gen_util
import os

"""
Generate small test case that kills the greedy algorithm.
"""
def gen_kill_greedy(knapsack_capacity):
  weights = [1, knapsack_capacity]
  values = [2, 3]
  write(knapsack_capacity, weights, values, 'kill_greedy_{0}'.format(knapsack_capacity))

"""
Generate a random test case.
"""
def gen_random(knapsack_capacity, n, max_weight, max_value, overwrite = True):
  weights = [ ]
  values = [ ]
  for i in range(n):
    w = random.randint(1, max_weight)
    v = random.randint(1, max_value)
    weights.append(w)
    values.append(v)
  if not overwrite:
    filename = gen_util.find_next_filename('rnd_cap={0}_n={1}_mw={2}_mv={3}'.format(knapsack_capacity, n, max_weight, max_value))
  else:
    filename = 'rnd_cap={0}_n={1}_mw={2}_mv={3}'.format(knapsack_capacity, n, max_weight, max_value)
  write(knapsack_capacity, weights, values, filename)

"""
Generate a random test case with all ratios in a given range.
"""
def gen_random_ratios_in_range(knapsack_capacity, n, min_ratio, max_ratio, max_weight, max_value, overwrite = True):
  weights = [ ]
  values = [ ]
  for i in range(n):
    w = random.randint(1, max_weight)
    v = random.randint(1, max_value)
    while not gen_util.in_range(w / v, min_ratio, max_ratio):
      w = random.randint(1, max_weight)
      v = random.randint(1, max_value)
    weights.append(w)
    values.append(v)
  if not overwrite:
    filename = gen_util.find_next_filename('rndration_cap={0}_n={1}_rlb={2}_rub={3}_mw={4}_mv={5}'.format(knapsack_capacity, n, min_ratio, max_ratio, max_weight, max_value))
  else:
    filename = 'rndration_cap={0}_n={1}_rlb={2}_rub={3}_mw={4}_mv={5}'.format(knapsack_capacity, n, min_ratio, max_ratio, max_weight, max_value)
  write(knapsack_capacity, weights, values, filename)

"""
Generate all ratio = w / v.
"""
def gen_ratio(knapsack_capacity, n, w, v):
  d = gen_util.gcd(w, v)
  w //= d
  v //= d
  weights = [ ]
  values = [ ]
  max_weight = 0
  max_value = 0
  indexes = [(i + 1) for i in range(n)]
  random.shuffle(indexes)
  for i in indexes:
    weights.append(w * i)
    values.append(v * i)
    max_weight = max(max_weight, weights[-1])
    max_value = max(max_value, values[-1])
  filename = 'ratio_cap={0}_n={1}_w={2}_v={3}_mw={4}_mv={5}'.format(knapsack_capacity, n, w, v, max_weight, max_value)
  write(knapsack_capacity, weights, values, filename)

def gen_ratio_groups(knapsack_capacity, ratios, group_size):
  n = len(ratios) * group_size
  weights = [ ]
  values = [ ]
  max_weight = 0
  max_value = 0
  for w, v in ratios:
    d = gen_util.gcd(w, v)
    w //= d
    v //= d
    indexes = [(i + 1) for i in range(group_size)]
    for i in indexes:
      weights.append(w * i)
      values.append(v * i)
      print(weights[-1] / values[-1])
      max_weight = max(max_weight, weights[-1])
      max_value = max(max_value, values[-1])
  indexes = [i for i in range(n)]
  random.shuffle(indexes)
  ww = [ ]
  vv = [ ]
  for i in indexes:
    ww.append(weights[i])
    vv.append(values[i])
  filename = 'ratiogroup_cap={0}_n={1}_gs={2}_mw={3}_mv={4}'.format(knapsack_capacity, n, group_size, max_weight, max_value)
  write(knapsack_capacity, ww, vv, filename)

"""
Generate all ratio 1 but even weight except for one item
with w = v = 1. Make knapsack capacity odd so that item
must be taken.

Idea: by sorting by ratio this item might be anywhere so
any solution that fails to pick it will fail.
"""
def gen_ratio_even(knapsack_capacity, n):
  weights = [ ]
  values = [ ]
  max_weight = 0
  max_value = 0
  indexes = [2 * (i + 1) for i in range(n)]
  random.shuffle(indexes)
  for i in indexes:
    weights.append(i)
    values.append(i)
    max_weight = max(max_weight, weights[-1])
    max_value = max(max_value, values[-1])
  weights.append(1)
  values.append(1)
  if knapsack_capacity % 2 == 0:
    knapsack_capacity -= 1
  filename = 'ratio1even_cap={0}_n={1}_mw={2}_mv={3}'.format(knapsack_capacity, n, max_weight, max_value)
  write(knapsack_capacity, weights, values, filename)

"""
Write test case.
"""
def write(knapsack_capacity, weights, values, filename):
  f = open('./tests/' + filename + '.in', 'w')
  assert len(weights) == len(values)
  n = len(weights)
  f.write('{0} {1}\n'.format(knapsack_capacity, n))
  for i in range(n):
    f.write('{0} {1}\n'.format(weights[i], values[i]))
  f.close()

def gen_classic():
  random.seed(31)
  gen_kill_greedy(100)
  gen_kill_greedy(1000)
  gen_random(1000, 1000, 20, 20, False)
  gen_random(1000, 1000, 20, 20, False)
  gen_ratio(1000, 50, 3, 5)
  gen_ratio(1000, 1000, 1, 1)
  gen_ratio(1000, 100, 7, 5)
  gen_ratio_even(9, 500)
  gen_random_ratios_in_range(1000, 1000, 10, 20, 1000, 1000)
  gen_random_ratios_in_range(1000, 1000, 2, 4, 100, 100)
  gen_random_ratios_in_range(1000, 1000, 7, 8, 100, 100)
  ratios = [(1, 1), (2, 1), (3, 1), (4, 1), (5, 1)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(1, 1), (1, 2), (1, 3), (1, 4), (1, 5)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(3, 5), (7, 9), (4, 6), (11, 13), (4, 5)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(100, 95), (100, 96), (100, 97), (100, 98), (100, 99), (100, 101), (100, 102), (100, 103), (100, 104), (100, 105)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(y, x) for (x, y) in ratios]
  gen_ratio_groups(1000, ratios, 10)
  gen_random(25, 25, 25, 25)
  gen_random(50, 50, 50, 50)
  gen_random(75, 75, 75, 75)
  gen_random(100, 100, 100, 100)
  gen_random(1000, 1000, 1000, 1000)

def gen_memory_reduction():
  random.seed(31)
  gen_kill_greedy(100)
  gen_kill_greedy(1000)
  gen_kill_greedy(10000)
  gen_random(50000, 50000, 20, 20, False)
  gen_random(50000, 50000, 20, 20, False)
  gen_random(50000, 50000, 20, 20, False)
  gen_random(50000, 50000, 20, 20, False)
  gen_random(50000, 50000, 1000, 1000, False)
  gen_ratio(1000, 50, 3, 5)
  gen_ratio(1000, 1000, 1, 1)
  gen_ratio(1000, 100, 7, 5)
  gen_ratio_even(9, 500)
  gen_random_ratios_in_range(1000, 1000, 10, 20, 1000, 1000)
  gen_random_ratios_in_range(1000, 1000, 2, 4, 100, 100)
  gen_random_ratios_in_range(1000, 1000, 7, 8, 100, 100)
  ratios = [(1, 1), (2, 1), (3, 1), (4, 1), (5, 1)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(1, 1), (1, 2), (1, 3), (1, 4), (1, 5)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(3, 5), (7, 9), (4, 6), (11, 13), (4, 5)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(100, 95), (100, 96), (100, 97), (100, 98), (100, 99), (100, 101), (100, 102), (100, 103), (100, 104), (100, 105)]
  gen_ratio_groups(1000, ratios, 10)
  ratios = [(y, x) for (x, y) in ratios]
  gen_ratio_groups(1000, ratios, 10)

if __name__ == '__main__':
  if os.path.exists('./tests'):
    os.system('rm -r ./tests')
  os.system('mkdir ./tests')
  gen_memory_reduction()
