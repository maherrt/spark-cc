package org.test.spark
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object wordcount {
  def main(args: Array[String]) = {

    //Start the Spark context
    val conf = new SparkConf()
      .setAppName("WordCount")
      .setMaster("local")
    val sc = new SparkContext(conf)

    //Read some example file to a test RDD
    val test = sc.textFile("README1.md")

    test.flatMap { line => //for each line
      line.split(" ") //split the line in word by word. NB: we use flatMap, because we return a list
    }
    .map { word => //for each word
      (word,1) //Return a key/value tuple, with the word as key and 1 as value
    }
    .reduceByKey(_ + _) //Sum all of the value with same key
    .saveAsTextFile("README.counts1.txt") //Save to a text file
    print("test 1 \n")
    //Stop the Spark context
    sc.stop

  }
}