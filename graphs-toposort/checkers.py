from validator_tools import *

def check(input_file, output_file, given_file):
  input = read_file(input_file)
  g = input.read_graph()
  s, t = input.read_two_int()
  expected_ans = read_file(output_file)
  given_ans = read_file(given_file)
  if expected_ans.read_line() == 'impossible':
    if given_ans.read_line() != 'impossible':
      return False, 'expected impossible but go something else'
    return True, '' 
  path = given_ans.read_int_array()
  pathok, msg = check_path(s, t, g, path)
  if pathok:
    return True, ''
  return False, msg
