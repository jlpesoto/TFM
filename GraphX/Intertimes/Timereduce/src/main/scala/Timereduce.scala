
package spark.timereduce
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Timereduce {
def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("timereduce").getOrCreate()
    val sc = spark.sparkContext 
	val before = sc.textFile("input_before.txt").map(x => (x.split(" ")(0).toInt, x.split(" ")(1).toInt)).reduceByKey(_+_).sortByKey().map(x=> x._1 + " " + x._2)
	val during = sc.textFile("input_during.txt").map(x => (x.split(" ")(0).toInt, x.split(" ")(1).toInt)).reduceByKey(_+_).sortByKey().map(x=> x._1 + " " + x._2)
	val after = sc.textFile("input_after.txt").map(x => (x.split(" ")(0).toInt, x.split(" ")(1).toInt)).reduceByKey(_+_).sortByKey().map(x=> x._1 + " " + x._2)
	before.coalesce(1,true).saveAsTextFile("output_before.txt")
	during.coalesce(1,true).saveAsTextFile("output_during.txt")
	after.coalesce(1,true).saveAsTextFile("output_after.txt")
    spark.stop()
  }
}






