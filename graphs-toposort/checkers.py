def check(input_file, output_file, given_file):
  f = open(input_file, 'r')
  lines = [ line.strip() for line in f.readlines() ]
  data = lines[0].split(' ')
  n = int(data[0])
  m = int(data[1])
  g = [ set() for i in range(n) ]
  for i in range(m):
    data = lines[i + 1].split(' ')
    u = int(data[0])
    v = int(data[1])
    g[u].add(v)
  f = open(given_file, 'r')
  try:
    lines = [ line.strip() for line in f.readlines() ]
    if len(lines) != 1:
      return False, 'wrong number of lines: {0}'.format(len(lines))
    order = [ int(x) for x in lines[0].split(' ') ]
    nodeset = set()
    for x in order:
      nodeset.add(x)
      if not (0 <= x and x < n):
        return False, 'some nodes in the order are out of range: {0}'.format(x) 
    if len(nodeset) != n:
      return False, 'there are missing nodes in the order'
    pos = [ -1 for i in range(n) ]
    for i in range(n):
      pos[order[i]] = i
    for u in range(n):
      for v in g[u]:
        if pos[u] > pos[v]:
          return False, 'node {1} before node {0} but there is an edge from {0} to {1}'.format(u, v)
    return True, '' 
  except Exception as e:
    return False, 'wrong output format'
