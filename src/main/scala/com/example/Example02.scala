package com.example

import com.vividsolutions.jts.geom
import com.vividsolutions.jts.io.{WKTWriter, WKTReader, WKBWriter, WKBReader}

import play.api.libs.json._
import play.extras.geojson._


object Example02 {

  // case class GeometryBean(id: Long, geom: Geometry)
  val wktReader = new WKTReader()
  val wktWriter = new WKTWriter()

  def main(args: Array[String]): Unit = {
    val POINT = "POINT(151.2111 -33.86)"
    val point = wktReader.read(POINT).asInstanceOf[geom.Point]
    val coord = point.getCoordinate
    println(point)

    val sydney = Feature(Point(LatLng(coord.y, coord.x)), properties=Some(Json.obj("name" -> "Sydney")))
    val json = Json.toJson(sydney)

    println(json)
  }
}
