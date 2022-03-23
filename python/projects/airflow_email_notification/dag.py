from datetime import datetime, timedelta

from airflow import DAG
from airflow.models import Variable, Connection, DagRun, TaskInstance
from airflow.operators.python import PythonOperator

sc_interval_var = Variable.get('schedule_interval_default', deserialize_json=True)

def t1():
    print('t1')
    raise Exception()

def t2():
    print('t2')

def email_alert(context: dict):
    from email.message import EmailMessage
    import smtplib

    try:
        email_conn = Connection.get_connection_from_secrets('sys_alert')
        email_list: dict = Variable.get('addresses_3_support_lines', deserialize_json=True)

        dag_run: DagRun = context.get('dag_run')
        # task_instance: TaskInstance = context['task_instance']

        if dag_run.dag_id in email_list.keys():
            email_list = email_list[dag_run.dag_id]
        else:
            email_list = email_list['default']

        msg = EmailMessage()
        msg['From'] = email_conn.login
        msg['To'] = email_list

        msg['Subject'] = 'FAILED DAG'
        body = '<h1>OMG!</h1>'

        msg.add_header('Content-Type', 'text/html')
        msg.set_payload(body, 'utf-8')

        server = smtplib.SMTP_SSL(email_conn.host, email_conn.port)
        server.login(email_conn.login, email_conn.get_password())
        print(f'Result: ', server.sendmail(msg['From'], msg['To'], msg.as_string()))
        server.quit()
    except Exception as e:
        print(f'Email alert fail: {e}')

with DAG(
    dag_id='test_email_notification',
    schedule_interval=timedelta(
        seconds=sc_interval_var['seconds'],
        minutes=sc_interval_var['minutes'],
    ),
    start_date=datetime(2022, 3, 20),
    catchup=False,
    on_failure_callback=email_alert,
    tags=['email','alert']
) as dag:
    t1 = PythonOperator(
        task_id='t1',
        python_callable=t1,
    )
    t2 = PythonOperator(
        task_id='t2',
        python_callable=t2,
    )

    t1 >> t2
