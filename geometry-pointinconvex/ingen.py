from gen_geometry import *
from IO import *
import random

def gen_random(n, q, MAX_X, MAX_Y):
  pol = gen_convex_polygon(n, MAX_X, MAX_Y)
  if random.randint(0, 1) == 1:
    pol.reverse()
  w = Writer()
  w.write_int(n)
  w.write_polygon(pol)
  w.write_int(q)
  for i in range(q):
    x = random.randint(0, MAX_X)
    y = random.randint(0, MAX_Y)
    w.write_tuple((x, y))
  w.write_file('tests/rnd_n={0}_q={1}_xmax={2}_ymax={3}.in'.format(n, q, MAX_X, MAX_Y))

def gen_random_all_q(n, MAX_X, MAX_Y):
  pol = gen_convex_polygon(n, MAX_X, MAX_Y)
  w = Writer()
  w.write_int(n)
  w.write_polygon(pol)
  w.write_int((MAX_X + 1) * (MAX_Y + 1))
  for x in range(0, MAX_X + 1):
    for y in range(0, MAX_Y + 1):
      w.write_tuple((x, y))
  w.write_file('tests/rnd_all_query_n={0}_xmax={1}_ymax={2}.in'.format(n, MAX_X, MAX_Y))

def gen_random_all_q_rev(n, MAX_X, MAX_Y):
  pol = gen_convex_polygon(n, MAX_X, MAX_Y)
  pol.reverse()
  w = Writer()
  w.write_int(n)
  w.write_polygon(pol)
  w.write_int((MAX_X + 1) * (MAX_Y + 1))
  for x in range(0, MAX_X + 1):
    for y in range(0, MAX_Y + 1):
      w.write_tuple((x, y))
  w.write_file('tests/rnd_all_query_rev_n={0}_xmax={1}_ymax={2}.in'.format(n, MAX_X, MAX_Y))


for i in range(10, 21):
  gen_random(i, i, i, i)

gen_random(10, 1000, 1000, 1000)
gen_random(50, 1000, 1000, 1000)
gen_random(100, 1000, 1000, 1000)
gen_random(500, 1000, 1000, 1000)
gen_random(1000, 1000, 1000, 1000)

gen_random_all_q(5, 5, 5)
gen_random_all_q_rev(5, 5, 5)
gen_random_all_q(10, 10, 10)
gen_random_all_q_rev(10, 10, 10)

