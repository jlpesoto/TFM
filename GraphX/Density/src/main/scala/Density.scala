
package spark.density
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Density {
def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Density").getOrCreate()
    val sc = spark.sparkContext 
	val degrees = sc.textFile("degrees.txt").map(x => (x.toInt, 1)).reduceByKey(_+_)
	val outdegrees = sc.textFile("outdegrees.txt").map(x => (x.toInt, 1)).reduceByKey(_+_)
	val indegrees = sc.textFile("indegrees.txt").map(x => (x.toInt, 1)).reduceByKey(_+_)
	val total_degrees = degrees.values.sum
	val total_outdegrees = outdegrees.values.sum
	val total_indegrees = indegrees.values.sum
	degrees.coalesce(1,true).map(x => x._1 + " " + x._2/total_degrees).saveAsTextFile("output_degrees")
	outdegrees.coalesce(1,true).map(x => x._1 + " " + x._2/total_outdegrees).saveAsTextFile("output_outdegrees")
	indegrees.coalesce(1,true).map(x => x._1 + " " + x._2/total_indegrees).saveAsTextFile("output_indegrees")
    spark.stop()
  }
}
