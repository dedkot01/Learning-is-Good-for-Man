from pyspark.sql import SparkSession
import datetime
import os
import pyspark
from pyspark.sql import SparkSession
from pyspark.sql.types import Row
from pyspark import SparkConf
from pyspark import SparkContext
from pyspark.sql import functions as f

appName = "PySpark anti fraud"
master = 'local[*]'

spark = SparkSession \
    .builder \
    .appName(appName) \
    .master(master) \
    .getOrCreate()

data = [{"1", "1", datetime.datetime.now(), True, True, "оплата", 999.00,
         ("000001", "Tatyana", "Lisova", ("lt@yandex.ru", "89998008800")), "831", ("nn", "пк", "пк", False)}]

schema_json = """
{
    "sesionId": "string",
    "eventId": "string",
    "eventTime": Timestamp,
    "pinWasEntred": Bool,
    "nfc": Bool,
    "operationType": "string", --[перевод, оплата, получение]
    "sum": Decimal,
    "userData":{
        "user_id": "string",
        "name": "string",
        "surname: "string"
        "contact"?:{
            "email": "string",
            "phoneNumber": "string"
        }
    },
    "region": "string",
    "deviceData": {
        "ipAddress": "string",
        "deviceModel": "string",
        "deviceImei": "string",
        "anotherSignInOnThisDevice": "boolean"
    }
}
"""

data2 = [
    Row(id=1, name="Tatyana", age=23),
    Row(id=2, name="Ivan", age=24)
]
df = spark.createDataFrame(data2)
df.show()
