import sys
import re

pattern = r".*z.{3}z.*"

for line in sys.stdin:
    line = line.rstrip()
    if re.match(pattern, line) is not None: print(line)