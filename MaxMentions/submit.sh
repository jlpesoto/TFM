spark-submit \
  --class "MaxMentions" \
  --master local[4] \
  target/scala-2.11/maxmentions_2.11-1.0.jar
