import requests

pattern = r'<a .*href.*=.*".*">'
res = requests.get("https://pastebin.com/raw/7543p0ns")
