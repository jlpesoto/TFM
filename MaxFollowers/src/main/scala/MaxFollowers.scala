import org.apache.spark.sql.SparkSession
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object MaxFollowers {
  def main(args: Array[String]) {
    val spark = SparkSession.builder.appName("MaxFollowers").getOrCreate()
    val sc = spark.sparkContext	    
	//Se leen los datos y se crea un conjunto de datos con la estructura ((Int, Int))
	//Se entiende que los datos son: user_a user_b tal que el user_a es seguido por el user_b
	val datos = sc.textFile("input.gz").map(x => (x.split(" ")(0).toInt,x.split(" ")(1).toInt)) 
	val usuario_max = datos.map(x=>(x._1, 1)).reduceByKey(_+_).sortBy(_._2, false).map(x=>x._1).first
	val usuarios = datos.map(x=>(x._1, 1)).reduceByKey(_+_).sortBy(_._2, false).map(x=>x._1)
	//map: En la primera columna se localizan los usuarios, por lo que se crea un nuevo rdd donde aparecen los usuarios y se contabiliza 		con un 1 cada vez que aparecen por lo que la estructura queda (user_a, 1) 
	//reduceByKey: sumamos todos los seguidores del user que se repiten
	//sortBy: los ordenamos de mayor a menor
	println(" El usuario con más seguidores es: " + usuario_max)
	//Número medio de followers
	val suma = datos.map(x=>(x._1, 1)).reduceByKey(_+_).map(x=>x._2).reduce(_+_)
	val total =  datos.map(x=>(x._1, 1)).reduceByKey(_+_).map(x=>x._2).count
	println("El número medio de seguidores es : " + suma/total)
	usuarios.saveAsTextFile("output.txt")

    spark.stop()
  }
}





