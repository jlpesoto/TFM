spark-submit \
  --class "spark.tweets.Tweets" \
  --master local[16] \
  --executor-memory 6g \
  --driver-memory 5g \
  target/scala-2.11/tweets_2.11-1.0.jar
