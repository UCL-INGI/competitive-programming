from validator_tools import *

def check(input_file, output_file, given_file):
  input = read_file(input_file)
  n, m = input.read_two_int()
  maze = input.read_k_lines(n)
  given_ans = read_file(given_file)
  path = [ ]
  while given_ans.has_next():
    i, j = given_ans.read_two_int()
    path.append((i, j))
  start = None
  end = None
  for i in range(n):
    for j in range(m):
      if maze[i][j] == 'S':
        start = (i, j)
      if maze[i][j] == 'T':
        end = (i, j)
  if path[0] != start:
    return False, 'wrong starting point: {0}'.format(path[0])
  if path[-1] != end:
    return False, 'wrong ending point: {0}'.format(path[-1])
  for k in range(1, len(path)):
    prev = path[k - 1]
    next = path[k]
    dist = abs(prev[0] - next[0]) + abs(prev[1] - next[1])
    if dist != 1:
      return False, 'wrong step in the maze: from {0} to {1}'.format(prev, next)
  for i, j in path:
    if maze[i][j] == '#':
      return False, 'the path visits a wall: {0}'.format((i, j))
  return True, ''
