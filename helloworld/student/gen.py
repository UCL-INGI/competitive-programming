import random

alphabet = 'abcdefghijklmnopqrstuvwxyz'

def generate(n, fn):
  f = open(fn, 'w')
  s = ''
  for i in range(n):
    s += alphabet[random.randint(0, 25)]
  f.write(s)
  f.write('\n')
  f.close()

if __name__ == '__main__':
  generate(10)
