from pyspark.sql import SparkSession

if __name__ == '__main__':
    spark = SparkSession.builder.getOrCreate()

    feeds = spark.read.json('feeds_show.json')
    feeds.printSchema()
    print(feeds.count())
