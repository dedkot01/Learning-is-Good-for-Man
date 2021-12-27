from pyspark.sql import SparkSession
from pyspark.sql.functions import explode
from pyspark.sql.functions import split

if __name__ == '__main__':
    spark = SparkSession.builder.appName('structured-streaming').getOrCreate()

    lines = spark.readStream\
        .format('socket')\
        .options(
            host='localhost',
            port=9999
        )\
        .load()
    words = lines.select(
        explode(split(lines.value, " ")).alias("word")
    )
    wordCounts = words.groupBy("word").count()

    query = wordCounts\
        .writeStream\
        .outputMode('append')\
        .format('console')\
        .start()
    query.awaitTermination()