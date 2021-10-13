import os
import os.path

result = []
for current_dir, dirs, files in os.walk(".\main"):
    for f in files:
        if f.endswith('.py'):
            result.append(current_dir[2::] + '\n')
            break

with open('answer.txt', 'w') as w:
    w.writelines(result)