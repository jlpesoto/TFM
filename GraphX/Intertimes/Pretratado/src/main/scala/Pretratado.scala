
package spark.pretratado
import org.apache.spark.graphx.GraphLoader
import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Pretratado {
def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("pretratado").getOrCreate()
    val sc = spark.sparkContext 
	val datos = sc.textFile("input.txt").map(x => (x.split(" ")(0).toString,x.split(" ")(1).toInt)).map( x => ((x._1(4).toInt - 48)*86400  + x._1.substring(5,10).toInt , x._2)).sortByKey()
	val before = datos.filter(x => x._1>86400 && x._1<172800).map(x=> x._1 + " " + x._2)
	val during = datos.filter(x => x._1>172800 && x._1<345600).map(x=> x._1 + " " + x._2)
	val after = datos.filter(x => x._1>345600).map(x=> x._1 + " " + x._2)
	
	before.coalesce(1,true).saveAsTextFile("output_before.txt")
	during.coalesce(1,true).saveAsTextFile("output_during.txt")
	after.coalesce(1,true).saveAsTextFile("output_after.txt")
    spark.stop()
  }
}






