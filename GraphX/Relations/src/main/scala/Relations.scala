import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Relations {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("Relations").getOrCreate()
    val sc = spark.sparkContext	    

	val datos = sc.textFile("input.gz").map( x => x.split(" ")(0) + " " +  x.split(" ")(1))
	datos.saveAsTextFile("output.txt")

    spark.stop()
  }
}





