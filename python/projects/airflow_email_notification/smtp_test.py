from email.message import EmailMessage
import smtplib
import credits

msg = EmailMessage()
msg['Subject'] = 'New'
msg['From'] = 'dedkot01@gmail.com'
msg['To'] = 'themordreid@gmail.com'

body = '''
<h1>Good, World!</h1>
'''
msg.add_header('Content-Type', 'text/html')
msg.set_payload(body, 'utf-8')

emails = msg['To']

smtp_host = 'smtp.gmail.com'
smtp_port = 465

server = smtplib.SMTP_SSL(smtp_host, smtp_port)
server.login(credits.sender_email, credits.password)
print(f'Result: ', server.sendmail(msg['From'], emails, msg.as_string()))
server.quit()
