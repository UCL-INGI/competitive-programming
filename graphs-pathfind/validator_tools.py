import sys
import math
import traceback

class Input:

  def __init__(self, lines):
    self.lines = lines
    self.index = 0

  def read_line(self):
    ret = self.lines[self.index]
    self.index += 1
    return ret

  def read_int_array(self):
    if len(self.lines[self.index]) == 0:
      self.index += 1
      return [ ]
    ret = [ int(x) for x in self.lines[self.index].split(' ') ]
    self.index += 1
    return ret

  def read_int(self):
    ret = int(self.lines[self.index])
    self.index += 1
    return ret

  def read_two_int(self):
    ret = self.read_int_array()
    return ret[0], ret[1]

  def read_graph(self):
    n, m = self.read_two_int()
    edges = [ ]
    for i in range(m):
      u, v = self.read_two_int()
      edges.append((u, v))
    return Graph(n, m, edges)

  def has_next(self):
    return self.index < len(self.lines)

  def equal(self, other):
    if len(self.lines) != len(other.lines):
      return False, 'number of lines'
    for i in range(len(self.lines)):
      if self.lines[i] != other.lines[i]:
        return False, 'line {0}: [{1}] != [{2}]'.format(i, self.lines[i], other.lines[i]) 
    return True, ''

  def __str__(self):
    prev = 'none'
    if self.index - 1 >= 0: prev = self.lines[self.index - 1]
    next = 'none'
    if self.index + 1 < len(self.lines): next = self.lines[self.index + 1]
    s = 'prev -> {0}\n'.format(prev)
    s += 'cur  -> {0}\n'.format(self.lines[self.index])
    s += 'next -> {0}\n'.format(next)
    return s

class Graph:

  def __init__(self, n, m, edges):
    self.n = n
    self.m = m
    self.edges = edges
    self.edge_set = set()
    self.g = [ [ ] for i in range(n) ]
    for e in edges:
      self.edge_set.add(e)
      self.g[e[0]].append(e[1])

  def contains(self, edge):
    return edge in self.edge_set

  def is_path(self, path):
    for i in range(1, len(path)):
      if not (path[i - 1], path[i]) in self.edge_set:
        return False
    return True

def read_file(filename):
  f = open(filename, 'r')
  return Input([line.strip() for line in f.readlines()])

def read_stdin():
  return Input([line.strip() for line in sys.stdin])

def check_path(s, t, graph, path):
  if path[0] != s:
    return False, 'path should start at {0} but starts at {1}'.format(s, path[0])
  if path[-1] != t:
    return False, 'path should end at {0} but ends at {1}'.format(t, path[-1])
  if not graph.is_path(path):
    return False, 'the provided path {0} is not a path in the graph'.format(path)
  return True, ''
