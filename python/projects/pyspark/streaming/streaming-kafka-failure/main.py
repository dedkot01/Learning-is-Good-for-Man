from pyspark.sql import SparkSession


def main():
    spark = (SparkSession
             .builder
             .appName('kafka-fault-tolerance')
             .getOrCreate())
    spark.sparkContext.setLogLevel('WARN')

    # Источник - кафка топик "eva00",
    # отключенный параметр "failOnDataLoss" не прерывает джобу, если топик был удалён
    # https://spark.apache.org/docs/latest/structured-streaming-kafka-integration.html 
    df = (spark
          .readStream
          .format('kafka')
          .option('kafka.bootstrap.servers', 'localhost:9092')
          .option('subscribe', 'eva00')
          .option('failOnDataLoss', 'false')
          .load())

    # Выход - кафка топик "eva01"
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
