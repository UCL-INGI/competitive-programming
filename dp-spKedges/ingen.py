from gen import *
from math import ceil

def write(fn, n, A, B, k, edges):
  f = open(fn, 'w')
  f.write('{0} {1} {2} {3} {4}\n'.format(n, len(edges), A, B, k))
  for u, v, w in edges:
    f.write('{0} {1} {2}\n'.format(u, v, w))
  f.close()

def gen_tower(k):
  k_init = k
  v = 1
  edges = [ ]
  w = 1
  while k > 1:
    v += 1
    edges.append((0, v, w))
    for _ in range(k - 2):
      edges.append((v, v + 1, w))
      v += 1
    edges.append((v, 1, w)) 
    w = ceil(w * k / (k - 1))
    k -= 1
  edges.append((0, 1, w + 1))
  for x in range(1, k_init + 1):
    write('./tests/tower_{0}_{1}.in'.format(k_init, x), v + 1, 0, 1, x, edges)

gen_tower(5)
  
"""
for n in [2, 3, 4, 5, 6, 7, 8, 9, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000]:
  m = min(n * (n - 1) // 2, 5 * n)
  n, edges = generate_random_weighted_connected_graph(n, m, 1, 10)
  write('./tests/rnd_{0}_{1}.in'.format(n, m), n, 0, n - 1, min(n - 1, random.randint(1, 11)), edges)
"""

