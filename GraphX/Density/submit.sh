spark-submit \
  --class "spark.density.Density" \
  --master local[16] \
  --executor-memory 6g \
  --driver-memory 5g \
  target/scala-2.11/density_2.11-1.0.jar
