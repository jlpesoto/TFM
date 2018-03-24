
package spark.degrees
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Degrees {
def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Degrees").getOrCreate()
    val sc = spark.sparkContext 
    val graph = GraphLoader.edgeListFile(sc, "input.txt")
    val ranks = graph.pageRank(0.0001).vertices
    val degrees=graph.degrees
    val indegrees= graph.inDegrees
    val outdegrees= graph.inDegrees
    indegrees.coalesce(1,true).map(x=>x._2).saveAsTextFile("output_indegrees")
    outdegrees.coalesce(1,true).map(x=>x._2).saveAsTextFile("output_outdegrees")
    degrees.coalesce(1,true).map(x=>x._2).saveAsTextFile("output_degrees")
    spark.stop()
  }
}
