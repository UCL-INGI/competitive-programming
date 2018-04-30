from validator_tools import *

def check(input_file, output_file, given_file):
  input = read_file(input_file)
  g = input.read_graph()
  expected_ans = read_file(output_file)
  given_ans = read_file(given_file)
  nb_scc_exp = expected_ans.read_int()
  nb_scc_given = given_ans.read_int()
  if nb_scc_exp != nb_scc_given:
    return False, 'expected {0} scc but got {1}'.format(nb_scc_exp, nb_scc_given)
  nbscc = nb_scc_exp
  scc_exp = set()
  for i in range(nbscc):
    s = expected_ans.read_int_array()
    s.sort()
    scc_exp.add(tuple(s))
  scc_given = set()
  for i in range(nbscc):
    s = given_ans.read_int_array()
    s.sort()
    scc_given.add(tuple(s))
  if len(scc_given) != nbscc:
    return False, 'the number of scc provided does not match'
  for x in scc_exp:
    if x not in scc_given:
      return False, 'expected scc {0} but not found'.format(x)
  return True, ''
