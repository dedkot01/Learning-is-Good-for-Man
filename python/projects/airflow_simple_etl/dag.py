from datetime import datetime

from airflow import DAG
from airflow.operators.python import PythonVirtualenvOperator

from minio_get_file import main as extract_func

from minio_put_file import main as load_func

from pandas_simple_transform import main as transform_func

with DAG(
    dag_id='etl_minio_input_bucket',
    schedule_interval='15 minutes',
    start_date=datetime(2021, 12, 30),
    catchup=False,
    tags=['minio', 'pandas'],
) as dag:
    extract = PythonVirtualenvOperator(
        task_id='extract',
        requirements=['minio==7.1.2'],
        system_site_packages=False,
        python_version='3.10.1',
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
        python_version='3.10.1',
        python_callable=transform_func,
        op_kwargs={
            'file': '201170.csv',
        },
    )
    load = PythonVirtualenvOperator(
        task_id='load',
        requirements=['minio==7.1.2'],
        system_site_packages=False,
        python_version='3.10.1',
        python_callable=load_func,
        op_kwargs={
            'bucket_name': 'output',
            'object_name': 'output.csv',
            'file_path': './output.csv',
        },
    )

    extract >> transform >> load
