import smtplib
from datetime import datetime, timedelta
from email.message import EmailMessage
from functools import reduce
from urllib.parse import urlencode

from airflow import DAG
from airflow.configuration import AirflowConfigParser
from airflow.models import Connection, DagRun, Variable
from airflow.operators.python import PythonOperator


sc_interval_var = Variable.get('schedule_interval_default', deserialize_json=True)


def t1():
    print('t1')
    raise Exception()


def t2():
    print('t2')


def email_alert(context: dict):

    try:
        airflow_conf: AirflowConfigParser = context['conf']

        email_conn = Connection.get_connection_from_secrets('sys_alert')
        email_list: dict = Variable.get('addresses_3_support_lines', deserialize_json=True)

        url_webserver = airflow_conf.get(section='webserver', key='base_url')  #Variable.get('url_webserver_airflow')

        dag_run: DagRun = context.get('dag_run')
        task_instances = dag_run.get_task_instances()

        failed_task_instances = [f'{ti.task_id}' for ti in task_instances if ti.current_state() == 'failed']
        task_instances = [f'{ti.task_id} - {ti.current_state()}' for ti in task_instances]

        links_to_logs_failed_ti = list()
        for ti in failed_task_instances:
            query_param = urlencode({'dag_id': dag_run.dag_id,
                                     'task_id': ti,
                                     'execution_date': dag_run.execution_date.strftime('%Y-%m-%dT%H:%M:%S.%f+00:00')})
            links_to_logs_failed_ti.append(f'<a href="{url_webserver}/log?{query_param}">{ti}</a>')
        links_to_logs_failed_ti = str(reduce(lambda x, y: f'{x}, {y}', links_to_logs_failed_ti))

        email_list = email_list.get(dag_run.dag_id, email_list['default'])

        msg = EmailMessage()
        msg['From'] = email_conn.login
        msg['To'] = email_list

        subject = f'FAILED DAG: {dag_run.dag_id}'
        body = f'''
            <div>Failed tasks: {links_to_logs_failed_ti}</div>
            <div>Execution date: {dag_run.execution_date}</div>
            <div>All tasks status: {str(reduce(lambda x, y: f'{x}, {y}', task_instances))}</div>
            <div>Link to dag: {url_webserver}/tree?{urlencode({'dag_id':dag_run.dag_id})}
        '''

        msg['Subject'] = subject
        msg.add_header('Content-Type', 'text/html')
        msg.set_payload(body, 'utf-8')

        with smtplib.SMTP_SSL(email_conn.host, email_conn.port) as server:
            server.login(email_conn.login, email_conn.get_password())
            print('Result: ', server.sendmail(msg['From'], msg['To'], msg.as_string()))
    except Exception as e:
        print(f'Email alert fail: {e}')
        Variable.set(key='exception', value=e)


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
