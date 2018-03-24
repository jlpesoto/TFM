import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Time {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Time").getOrCreate()
    val sc = spark.sparkContext	    
	
	val datos = sc.textFile("input.gz").map(x => x.split(" ")(2).toInt + " " + 1) 
	datos.saveAsTextFile("output.txt")

    spark.stop()
  }
}





