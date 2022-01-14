from pyspark.sql import SparkSession
from pyspark.sql import functions as F


def main() -> None:
    spark = SparkSession.builder.getOrCreate()

    names = spark.read.csv('./data1.csv', header=True)
    names.printSchema()
    names.show()

    collect_list = names.agg(F.collect_set(names['name']).alias('result'))
    collect_list.printSchema()
    collect_list.show()

    collect_list.write.json('output.json', 'overwrite')

    names = names.withColumn('timestamp', names['time'])
    df_window = (names
                 .withWatermark('timestamp', '1 minutes')
                 .groupBy(
                     F.window('timestamp', '1 minutes'),
                     names['name'].alias('user'),
                 )
                 .agg(F.approx_count_distinct(names['country']).alias('count'))
                 )

    df_window.printSchema()
    df_window.show()

    join_df = (names
               .join(df_window, on=names['name'] == df_window['user'])
               .where(names['timestamp'].between(df_window['window.start'], df_window['window.end']))
               )

    join_df.printSchema()
    join_df.show()

    fin = join_df.where(join_df['count'] > 1)

    fin.show()

    result = (fin
              .groupBy(fin['name'], fin['window.start'].alias('start_time'), fin['count'])
              .agg(F.collect_set(fin['country']).alias('arr_country'))
              .withColumn('myCol', F.lit('myString'))
              )

    result.printSchema()
    result.show()

    result.write.json('.output', 'overwrite')


if __name__ == '__main__':
    main()
