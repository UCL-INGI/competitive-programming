def genall(n):
  for i in range(1, n + 1):
    f = open('./tests/{0}_{1}.in'.format(n, i), 'w')
    f.write('{0} {1}'.format(n, i))
    f.close()
