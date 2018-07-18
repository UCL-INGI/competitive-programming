import subprocess

cmd = ['sleep', '10']
try:
  subprocess.run(cmd, timeout=1)
except subprocess.TimeoutExpired:
  print('process ran too long')
