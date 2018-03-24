spark-submit \
  --class "spark.degrees.Degrees" \
  --master local[16] \
  --executor-memory 6g \
  --driver-memory 5g \
  target/scala-2.11/degrees_2.11-1.0.jar
