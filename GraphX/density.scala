val datos = sc.textFile("/home/joselucas/Escritorio/prueba.txt").map(x => (x.toInt, 1)).reduceByKey(_+_)
val total = datos.values.sum
val resultado = datos.map(x => (x._1, x._2/total))
