def gen_range_repreat(min, max, rep):
  a = [ ]
  for i in range(min, max + 1):
    for k in range(rep):
      a.append(i)
  f = open('tests/range_rep_{0}_{1}_{2}.in'.format(min, max, rep), 'w')
  f.write('{0} {1}\n'.format(len(a), len(a) // rep))
  for i in range(len(a)):
    f.write(str(a[i]))
    if i < len(a) - 1:
      f.write(' ')
  f.write('\n')
  for i in range(min, max + 1):
    f.write(str(i))
    f.write('\n')
  f.close()

gen_range_repreat(0, 10, 1)
gen_range_repreat(0, 10, 2)
gen_range_repreat(0, 10, 3)

