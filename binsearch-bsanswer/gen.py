import random

def gen_random(n):
  pos = set()
  while len(pos) < n:
    x = random.randint(0, 10001)
    y = random.randint(0, 10001)
    pos.add((x, y))
  f = open('tests/rnd_{0}.in'.format(n), 'w')
  f.write('{0}\n'.format(n))
  for x, y in pos:
    f.write('{0} {1}\n'.format(x, y))
  f.close()  


gen_random(10)
gen_random(20)
gen_random(30)
gen_random(40)
gen_random(50)
gen_random(60)
gen_random(70)
gen_random(80)
gen_random(90)
gen_random(100)
gen_random(200)
gen_random(300)
gen_random(400)
gen_random(500)
gen_random(600)
gen_random(700)
gen_random(800)
gen_random(900)
gen_random(1000)

