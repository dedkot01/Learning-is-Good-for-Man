from datetime import datetime, timedelta

from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonVirtualenvOperator

from minio_get_file import main as extract_func

from minio_put_file import main as load_func

from pandas_simple_transform import main as transform_func

with DAG(
    dag_id='etl_minio_input_bucket',
    schedule_interval=timedelta(minutes=15),
    start_date=datetime(2021, 12, 30),
    catchup=False,
    tags=['minio', 'pandas'],
) as dag:
    extract = PythonVirtualenvOperator(
        task_id='extract',
        requirements=['minio==7.1.2'],
        system_site_packages=False,
        python_callable=extract_func,
        op_kwargs={
            'bucket_name': 'input',
            'object_name': '201170.csv',
            'file_path': './201170.csv',
        },
    )
    transform = PythonVirtualenvOperator(
        task_id='transform',
        requirements=['pandas==1.3.5'],
        system_site_packages=False,
        python_callable=transform_func,
        op_kwargs={
            'file': '201170.csv',
        },
    )
    load = PythonVirtualenvOperator(
        task_id='load',
        requirements=['minio==7.1.2'],
        system_site_packages=False,
        python_callable=load_func,
        op_kwargs={
            'bucket_name': 'output',
            'object_name': 'output.csv',
            'file_path': './output.csv',
        },
    )
    clean = BashOperator(
        task_id='clean',
        bash_command='rm 201170.csv output.csv',
    )

    extract >> transform >> load >> clean
