package com.example

import com.vividsolutions.jts.geom.{Coordinate, Envelope}
import com.vividsolutions.jts.index.kdtree.{KdNode, KdTree}

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}


/**
  * Created by kotarohara on 6/17/16.
  */
object KDTreeExample {
  def main(args: Array[String]): Unit = {
    example2
  }

  val example2: Unit = {
    var clusterIndex = 1
    val clusters = new mutable.HashMap[Coordinate, Int]
    val coordinates = readCSV("./resource/labels.csv", 2)
    val tree: KdTree = new KdTree(0.0)

    coordinates.foreach { coordinate =>
      tree.insert(coordinate)
    }

    // val p = coordinates(10)
    // println("R:", latLngOffset(p.y, 5, 0).mkString(", "))
    val radius = 5.78E-5  // Approximately 5 meters

    for (p <- coordinates) {
      val xMin = p.x - radius
      val xMax = p.x + radius
      val yMin = p.y - radius
      val yMax = p.y + radius
      val envelope = new Envelope(xMin, xMax, yMin, yMax)
      val result: Array[Coordinate] = tree.query(envelope).toArray.map(x => x.asInstanceOf[KdNode].getCoordinate)

      if (!clusters.contains(p)) {
        clusters.put(p, clusterIndex)
        result.foreach { coord =>
          if (!clusters.contains(coord)) {
            clusters.put(coord, clusterIndex)
          }
        }
        clusterIndex += 1
      }
    }


    val swapped = clusters.groupBy(_._2).mapValues(_.keys)
    for ((k, v) <- swapped) {
      val hc = v.head
      val xmean = v.map(_.x).sum / v.size
      val ymean = v.map(_.y).sum / v.size
      println(s"${ymean},${xmean}   ${k}, ${v}")
    }

//    for (c <- coordinates) {
//      println(s"${c.y},${c.x}")
//    }


  }

  val example1: Unit = {
    val tolerance = 0.0
    val kdt: KdTree = new KdTree(tolerance)
    kdt.insert(new Coordinate(0.0, 0.0))
    kdt.insert(new Coordinate(0.1, 0.0))
    kdt.insert(new Coordinate(1.0, 0.0))
    kdt.insert(new Coordinate(1.1, 0.0))

    val env = new Envelope(-0.5, 0.5, -0.5, 0.5)
    val list = kdt.query(env)
  }

  def readCSV(filename: String, labelType: Int): Array[Coordinate] = {
    case class Row(id: Int, labelType: Int, lat: Double, lng: Double)
    val bufferedSource = io.Source.fromFile(filename)
    val coordinates = for (line <- bufferedSource.getLines.drop(1)) yield {
      val cols = line.split(",").map(_.trim)
      val id: Int = cols(0).toInt
      val labelType: Int = cols(1).toInt
      val lat: Double = cols(2).toDouble
      val lng: Double = cols(3).toDouble
      Row(id, labelType, lat, lng)
    }
    coordinates.filter(_.labelType == labelType).map(d => new Coordinate(d.lng, d.lat)).toArray
  }

  def latLngOffset(lat: Double, dx: Double, dy: Double): Array[Double] = {
    import math._
    val dlat: Double = dy / 111111
    val dlng: Double = dx / (111111 * cos(toRadians(lat)))
    Array(dlat, dlng)
  }
}