import math

f = int(input())
sq = math.floor(math.sqrt(f))
next = sq
last = 1
while True:
  print('drop', next)
  state = int(input())
  if state == 0:
    last = next
    next += sq
  else:
    if last == next - 1:
      print('found', last + 1)
    else:
      for i in range(last + 1, next):
        print('drop', i)
        state = int(input())
        if state == 1:
          print('found', i)
          break
    break
