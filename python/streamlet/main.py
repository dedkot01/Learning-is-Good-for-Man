from pyspark.sql import SparkSession

spark = SparkSession.builder.appName("KafkaConsumer").getOrCreate()
spark.sparkContext.setLogLevel('ERROR')

df = spark\
    .readStream\
    .format('kafka')\
    .option('kafka.bootstrap.servers', 'localhost:9092')\
    .option('subscribe', 'eva00')\
    .load()
print(df.selectExpr('CAST(key AS STRING)', 'CAST(value AS STRING)'))