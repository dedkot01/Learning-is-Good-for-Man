from datetime import timedelta, datetime
from airflow import DAG
from airflow.operators.dummy import DummyOperator
from airflow.operators.branch import BaseBranchOperator
from airflow.operators.weekday import BranchDayOfWeekOperator

from typing import Iterable, Union

class BranchDayOfMonthOperator(BaseBranchOperator):
    
    def __init__(
        self,
        *,
        follow_task_ids_if_true: Union[str, Iterable[str]],
        follow_task_ids_if_false: Union[str, Iterable[str]],
        day: int,
        **kwargs,
    ) -> None:
        super().__init__(**kwargs)
        self.follow_task_ids_if_true = follow_task_ids_if_true
        self.follow_task_ids_if_false = follow_task_ids_if_false
        self.day = day
    
    def choose_branch(self, context):
        if context['execution_date'].day == self.day:
            return self.follow_task_ids_if_true
        else:
            return self.follow_task_ids_if_false

with DAG(
    dag_id='test-branch-day-of-week',
    tags=['test'],
    start_date=datetime.now(),
    schedule_interval=timedelta(
        minutes=5,
    ),
    catchup=False,
) as dag:
    t1 = DummyOperator(task_id='t1')
    today_tuesday = BranchDayOfWeekOperator(
        task_id='today_tuesday',
        week_day='Tuesday',
        follow_task_ids_if_true='t2',
        follow_task_ids_if_false='t3',
    )
    t2 = DummyOperator(task_id='t2')
    t3 = DummyOperator(task_id='t3')
    t4 = DummyOperator(task_id='t4', trigger_rule='none_failed')
    today_monday = BranchDayOfWeekOperator(
        task_id='today_monday',
        week_day='Monday',
        follow_task_ids_if_true='t5',
        follow_task_ids_if_false='t6'
    )
    t5 = DummyOperator(task_id='t5')
    t6 = DummyOperator(task_id='t6')

    t1 >> today_tuesday >> [t2, t3] >> t4 >> today_monday >> [t5, t6]

with DAG(
    dag_id='test-branch-day-of-month',
    tags=['test'],
    start_date=datetime.now(),
    schedule_interval=timedelta(
        minutes=5,
    ),
    catchup=False,
) as dag:
    t1 = DummyOperator(task_id='t1')
    today_first_day = BranchDayOfMonthOperator(
        task_id='today_first_day',
        day=1,
        follow_task_ids_if_true='t2',
        follow_task_ids_if_false='t3',
    )
    t2 = DummyOperator(task_id='t2')
    t3 = DummyOperator(task_id='t3')
    t4 = DummyOperator(task_id='t4', trigger_rule='none_failed')
    today_27_day = BranchDayOfMonthOperator(
        task_id='today_27_day',
        day=27,
        follow_task_ids_if_true='t5',
        follow_task_ids_if_false='t6'
    )
    t5 = DummyOperator(task_id='t5')
    t6 = DummyOperator(task_id='t6')

    t1 >> today_first_day >> [t2, t3] >> t4 >> today_27_day >> [t5, t6]
