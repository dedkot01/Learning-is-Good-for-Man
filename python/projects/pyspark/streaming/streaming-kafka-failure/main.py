from pyspark.sql import SparkSession


def main():
    spark = (SparkSession
             .builder
             .appName('kafka-fault-tolerance')
             .getOrCreate())
    spark.sparkContext.setLogLevel('WARN')

    df = (spark
          .readStream
          .format('kafka')
          .option('kafka.bootstrap.servers', 'localhost:9092')
          .option('subscribe', 'eva00')
          .option('failOnDataLoss', 'false')
          .load())

    (df
     .writeStream
     .format('kafka')
     .outputMode('append')
     .option('kafka.bootstrap.servers', 'localhost:9092')
     .option('topic', 'eva01')
     .option('checkpointLocation', './.local/kafka')
     .start()
     .awaitTermination())



if __name__ == '__main__':
    main()
