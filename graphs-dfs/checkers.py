from validator_tools import *

def check(input_file, output_file, given_file):
  input = read_file(input_file)
  g = input.read_graph()
  expected_ans = read_file(output_file)
  try:
    given_ans = read_file(given_file)
  except:
    return False, 'your output does not respect the output format'
  if given_ans.peek_line() == 'no':
    if expected_ans.read_line() != 'no':
      return False, 'got no but there is a cylce'
    return True, '' 
  cycle = given_ans.read_int_array()
  return check_cycle(g, cycle)
