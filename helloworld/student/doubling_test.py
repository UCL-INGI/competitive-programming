from gen import *
from run_tools import *

compile_java('yunoacsol')

N = 10
generate(N, 'rnd')
time_prev = run_java('yunoacsol', 'rnd', 'rnd_output')

N *= 2
generate(N, 'rnd')
time_next = run_java('yunoacsol', 'rnd', 'rnd_output')

ratio = time_next / time_prev
print(ratio)

for i in range(20):
  time_prev = time_next
  N *= 2
  generate(N, 'rnd')
  time_next = run_java('yunoacsol', 'rnd', 'rnd_output')
  ratio = time_next / time_prev
  print(ratio)
