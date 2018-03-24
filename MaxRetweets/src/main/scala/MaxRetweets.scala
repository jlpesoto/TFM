import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object MaxRetweets {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("MaxRetweets").getOrCreate()
    val sc = spark.sparkContext	    
	
	//se leen los dats que siguen una estructura user_A user_b n: el user_a ha retweeteado n veces al user_b
	val datos = sc.textFile("input.gz").map(x => (x.split(" ")(0).toInt,x.split(" ")(1).toInt,x.split(" ")(2).toInt)) 
	//se crea un rdd (Int, Int, Int)
	//persona más activa = persona que más retweets ha hecho
	val usuarios = datos.map(x=>(x._1, x._3)).reduceByKey(_+_).sortBy(_._2, false)
	//map: nos quedamos con las columnas de user_a y n_retweets
	//reduce: sumamos el numero de retweets de cada user_a
	//sort: ordenamos los user_a de mayor a menor en función de los retweets
	println("El usuario más activo es: " + usuarios.first)
	usuarios.saveAsTextFile("output.txt")

    spark.stop()
  }
}





