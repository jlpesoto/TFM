import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object MaxMentions {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("MaxMentions").getOrCreate()
    val sc = spark.sparkContext	    
	//Se leen los datos que tienen la estructura: user_a user_b n_mencions ; es decir el user_a ha mencionado n veces al user_b
	val datos = sc.textFile("input.gz").map(x => (x.split(" ")(0).toInt,x.split(" ")(1).toInt,x.split(" ")(2).toInt)) 
	//Se queda una estructura (Int, Int,Int) 

	//Usuarios más mencionados
	val usuarios = datos.map(x=>(x._2, x._3)).reduceByKey(_+_).sortBy(_._2, false)
	//map: nos quedamos con la columna de usarios mencionados y numero de menciones
	//reduce: se suman el numero de veces que se han mencionado
	//sort: se ordena de mas a menos menciones
	usuarios.map(x=>x._1).take(3).foreach(println)

	usuarios.saveAsTextFile("output.txt")

    spark.stop()
  }
}






