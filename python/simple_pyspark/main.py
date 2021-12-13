import datetime

from pyspark import Row
from pyspark.sql import SparkSession


def add_date(x: Row):
    x = x.asDict()
    x['date'] = datetime.date.fromtimestamp(x['timestamp'] // 1000)
    return x


if __name__ == '__main__':
    spark = SparkSession.builder.getOrCreate()

    feeds = spark.read.json('feeds_show.json')
    feeds.printSchema()
    print(feeds.count())

    rdd = feeds.rdd.map(add_date)

    dates = rdd.toDF()
    dates.printSchema()
    sql_dates = dates.createOrReplaceTempView('feeds')
    
