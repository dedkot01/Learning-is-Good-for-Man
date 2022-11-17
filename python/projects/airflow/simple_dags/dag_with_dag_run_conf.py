from datetime import datetime, timedelta

from airflow import DAG
from airflow.models.dagrun import DagRun
from airflow.operators.python import PythonOperator


def func(text: str, number: int) -> None:
    print(text)
    print(number)


def main(text, number, **kwargs):
    dag_run: DagRun = kwargs['dag_run']
    dag_run.external_trigger
    text = kwargs['dag_run'].conf.get('text', text)
    number = kwargs['dag_run'].conf.get('number', number)
    func(text, number)


with DAG(
    dag_id='dag_run_conf',
    schedule_interval=timedelta(seconds=30),
    start_date=datetime(2021, 12, 30),
    catchup=False,
) as dag:
    func_task = PythonOperator(
        task_id='func_task',
        python_callable=main,
        op_kwargs={
            'text': 'from_dag',
            'number': 1,
        },
        provide_context=True,
    )
