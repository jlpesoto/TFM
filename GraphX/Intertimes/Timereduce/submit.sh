spark-submit \
  --class "spark.timereduce.Timereduce" \
  --master local[16] \
  --executor-memory 6g \
  --driver-memory 5g \
  target/scala-2.11/timereduce_2.11-1.0.jar
