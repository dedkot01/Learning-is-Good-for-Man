from datetime import datetime, timedelta

from airflow import DAG
from airflow.operators.bash import BashOperator
from airflow.operators.python import PythonVirtualenvOperator
from airflow.models import Variable
from airflow.models import Connection

from minio_get_file import main as extract_func

from minio_put_file import main as load_func

from pandas_simple_transform import main as transform_func

sc_interval_var = Variable.get('schedule_interval_default', deserialize_json=True)
minio_connection = Connection.get_connection_from_secrets('minio')

with DAG(
    dag_id='etl_minio_input_bucket',
    schedule_interval=timedelta(
        seconds=sc_interval_var['seconds'],
        minutes=sc_interval_var['minutes'],
    ),
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
            'host': minio_connection.host,
            'port': minio_connection.port,
            'access_key': minio_connection.login,
            'secret_key': minio_connection.password,
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
        bash_command='clean.sh',
    )

    extract >> transform >> load >> clean
