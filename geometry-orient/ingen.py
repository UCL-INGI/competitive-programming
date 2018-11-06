def write(fn, Ax, Ay, Bx, By, Cx, Cy, Px, Py):
  f = open('tests/' + fn + '.in', 'w')
  f.write('{0} {1}\n'.format(Ax, Ay))
  f.write('{0} {1}\n'.format(Bx, By))
  f.write('{0} {1}\n'.format(Cx, Cy))
  f.write('{0} {1}\n'.format(Px, Py))
  f.close()

Ax = 1
Ay = 1
Bx = 7
By = 4
Cx = 4
Cy = 10

for x in range(0, 9):
  for y in range(0, 12):
    fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Bx, By, Cx, Cy, x, y)
    write(fn, Ax, Ay, Bx, By, Cx, Cy, x, y)
    fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Cx, Cy, Bx, By, x, y)
    write(fn, Ax, Ay, Cx, Cy, Bx, By, x, y)

