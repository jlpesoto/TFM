
package spark.tweets
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Tweets {
def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("tweets").getOrCreate()
    val sc = spark.sparkContext 
	val datos = sc.textFile("input.txt").map(x => (x.split(" ")(0).toString,x.split(" ")(1).toInt)).map( x => ((x._1(4).toInt - 48)*86400  + x._1.substring(5,10).toInt , x._2)).map(x => if(x._1 % 180==0) (x._1/86400,x._2) else ((((x._1/180).toInt+1)*180).toDouble/86400, x._2)).reduceByKey(_+_).map(x=> x._1 + " " + x._2/180)
	datos.coalesce(1,true).saveAsTextFile("output")
    spark.stop()
  }
}


