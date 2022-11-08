from datetime import timedelta, datetime
from airflow import DAG
from airflow.models import Variable
from airflow.operators.python import PythonOperator


test_dynamic_task_var = Variable.get('test_dynamic_task_var', deserialize_json=True)

def func(x):
    print(x)

with DAG(
    dag_id='test-dynamic-tasks',
    tags=['test'],
    start_date=datetime.now(),
    schedule_interval=timedelta(
        seconds=35,
    ),
    catchup=False,
) as dag:
    tasks = []
    for task in test_dynamic_task_var['tasks']:
        tasks.append(
            PythonOperator(
                task_id=task['name'],
                python_callable=func,
                op_kwargs={
                    'x': task['x'],
                },
            )
        )
