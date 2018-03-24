spark-submit \
  --class "MaxRetweets" \
  --master local[4] \
  target/scala-2.11/maxretweets_2.11-1.0.jar
