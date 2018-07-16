def gen_kill_greedy_bfs(k):
  assert k % 2 == 1
  edges = [ ]
  v = 0
  for i in range(k):
    if i % 2 == 0:
      edges.append((v, k + v, 1))
    else:
      edges.append((k + v, v, 1))
    v += 1
  v = 1
  for i in range(k // 2):
    edges.append((v, v + 1, 1))
    v += 2
  v = k
  for i in range(k // 2):
    edges.append((v, v + 1, 1))
    v += 2
  s = 2 * k
  t = s + 1
  v = t + 1
  edges.append((s, 0, 1))
  edges.append((2 * k - 1, t, 1))
  plen = 2 * k + 1
  for i in range(1, k):
    edges.append((s, v, 1))
    for j in range(plen - 2):
      edges.append((v, v + 1, 1))
      v += 1
    edges.append((v, i, 1))
    v += 1
  for i in range(k, 2 * k - 1):
    edges.append((i, v, 1))
    for j in range(plen - 2):
      edges.append((v, v + 1, 1))
      v += 1
    edges.append((v, t, 1))
    v += 1
  print('V={0} E={1}'.format(v, len(edges)))
  write('kill_greedy_bfs_{0}'.format(k), v, s, t, edges)

def write(filename, n, s, t, edges):
  f = open('tests/{0}.in'.format(filename), 'w')
  f.write('{0} {1}\n'.format(n, len(edges)))
  f.write('{0} {1}\n'.format(s, t))
  for u, v, c in edges:
    f.write('{0} {1} {2}\n'.format(u, v, c))
  f.close()

for k in range(3, 16, 2):
  gen_kill_greedy_bfs(k)
