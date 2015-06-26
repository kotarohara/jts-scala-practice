package com.example

import com.vividsolutions.jts.geom.{Geometry, Point}
import com.vividsolutions.jts.io.{WKTWriter, WKTReader, WKBWriter, WKBReader}


object Example02 {

  // case class GeometryBean(id: Long, geom: Geometry)
  val wktReader = new WKTReader()
  val wktWriter = new WKTWriter()
  val wkbWriter = new WKBWriter()
  val wkbReader = new WKBReader()

  def main(args: Array[String]): Unit = {
    val POINT = "POINT(-71.064544 42.28787)"
    val point = wktReader.read(POINT).asInstanceOf[Point]
    println("hi")
    println(point.toString)
  }
}
