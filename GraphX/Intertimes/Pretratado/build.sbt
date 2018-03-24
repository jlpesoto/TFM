name := "Pretratado"

version := "1.0"

scalaVersion := "2.11.8"

lazy val sparkVersion = "2.2.0"
lazy val spark = "org.apache.spark"

libraryDependencies ++= Seq(
  spark %% "spark-core" % sparkVersion,
  spark %% "spark-sql" % sparkVersion,
  spark %% "spark-streaming" % sparkVersion,
  spark %% "spark-graphx" % sparkVersion,
  "com.databricks" %% "spark-csv" % "1.3.0"

)


