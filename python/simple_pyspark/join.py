from pyspark.sql import SparkSession

if __name__ == '__main__':
    spark = SparkSession.builder.getOrCreate()

    t_a = [
        {'id': 1, 'user': 'Dima'},
        {'id': 2, 'user': 'Diana'},
        {'id': 3, 'user': 'Anton'},
    ]
    t_b = [
        {'id': 1, 'flag': 0},
        {'id': 1, 'flag': 1},
        {'id': 2, 'flag': 1},
        {'id': 3, 'flag': 0},
    ]

    ta = spark.createDataFrame(t_a)
    tb = spark.createDataFrame(t_b)

    ta.createOrReplaceTempView('t_a')
    tb.createOrReplaceTempView('t_b')

    over = spark.sql("SELECT *, FIRST_VALUE(flag) OVER(PARTITION BY id) AS is_first FROM t_b GROUP BY id")
    over.show()
