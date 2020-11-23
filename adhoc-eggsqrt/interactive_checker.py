import sys, traceback
import subprocess
import math

class Pipe:

  def __init__(self, name, ext):
    if   ext == 'java': command = ['java', name]
    elif ext == 'cpp':  command = ['./' + name]
    elif ext == 'py':   command = ['python3', name + '.py']
    self.p = subprocess.Popen(command, stdout=subprocess.PIPE, stdin=subprocess.PIPE)
  
  def send_data(self, data):
    self.p.stdin.write(str(data) + '\n')
    self.p.stdin.flush()

  def read_data(self):
    return self.p.stdout.readline().strip()

def egg_checker(input, name, ext):
  pipe = Pipe(name, ext)
  f = open(input, 'r')
  data = f.readlines()[0].split(' ')
  nb_floors = int(data[0])
  target = int(data[1])
  pipe.send_data(nb_floors)
  nb_drops = 0
  max_drop = 2 * math.floor(math.sqrt(nb_floors))
  try:
    while True:
      data = pipe.read_data()
      data = data.split(' ')
      floor = int(data[1])
      if data[0] == 'drop':
        nb_drops += 1
        if floor < target:
          pipe.send_data('0')
        else:
          pipe.send_data('1')
      elif data[0] == 'found':
        if floor == target: return True, 'all ok'
        return False, 'wrong floor'
      else:
        return False, 'wrong format'
      if nb_drops > max_drop:
        return False, 'max drop exceeded'
  except:
    return False, 'exception'

if __name__ == '__main__':
  input = sys.argv[1]
  name  = sys.argv[2]
  ext   = sys.argv[3]
  ok, msg = egg_checker(input, name, ext)
  f = open('verdict', 'w')
  if ok:
    f.write('ok\n')
  else:
    f.write('ko\n')
    f.write(msg + '\n')
  f.close()
