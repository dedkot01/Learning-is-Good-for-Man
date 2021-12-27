from pyspark import SparkConf
from pyspark.context import SparkContext

if __name__ == '__main__':
    conf = SparkConf()\
        .setAppName('Hello, World!')\
        .setMaster('local[2]')
    sc = SparkContext(conf=conf)