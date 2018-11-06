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

P = [
(0, 0),
(0,-1),
(1, 1),
(1, 6),
(2, 4),
(3, 2),
(3, 7),
(4,10),
(5, 3),
(5, 6),
(7, 2),
(7, 4)
]

for x, y in P:
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Bx, By, Cx, Cy, x, y)
  write(fn, Ax, Ay, Bx, By, Cx, Cy, x, y)
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Cx, Cy, Bx, By, x, y)
  write(fn, Ax, Ay, Cx, Cy, Bx, By, x, y)

Ax = 4
Ay = 1
Bx = 6
By = 2
Cx = 0
Cy = 2

P = [
(0, 2),
(2, 0),
(4, 0),
(4, 1),
(4, 4),
(6, 0),
(6, 2),
(8, 3),
(8, 4)
]

for x, y in P:
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Bx, By, Cx, Cy, x, y)
  write(fn, Ax, Ay, Bx, By, Cx, Cy, x, y)
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Cx, Cy, Bx, By, x, y)
  write(fn, Ax, Ay, Cx, Cy, Bx, By, x, y)

Ax = 1
Ay = 1
Bx = 4
By = 1
Cx = 1
Cy = 3

P = [
(0, 0),
(0, 2),
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 0),
(3, 1),
(3, 3),
(4, 1)
]

for x, y in P:
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Bx, By, Cx, Cy, x, y)
  write(fn, Ax, Ay, Bx, By, Cx, Cy, x, y)
  fn = 'A_{0}_{1}_B_{2}_{3}_C_{4}_{5}_P_{6}_{7}'.format(Ax, Ay, Cx, Cy, Bx, By, x, y)
  write(fn, Ax, Ay, Cx, Cy, Bx, By, x, y)

