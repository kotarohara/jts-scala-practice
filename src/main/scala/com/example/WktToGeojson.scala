package com.example

import com.vividsolutions.jts.geom
import com.vividsolutions.jts.io.{WKTWriter, WKTReader, WKBWriter, WKBReader}

import play.api.libs.json._
import play.extras.geojson._

// Example 2: Converting between WKB and Geojson

object WktToGeojson {

  // case class GeometryBean(id: Long, geom: Geometry)
  val wktReader = new WKTReader()
  val wktWriter = new WKTWriter()
  val wkbReader = new WKBReader()
  val wkbWriter = new WKBWriter(2, true)

  def main(args: Array[String]): Unit = {
    val POINT = "POINT(151.2111 -33.86)"
    val point = wktReader.read(POINT).asInstanceOf[geom.Point]
    val coord = point.getCoordinate
    println(point)

    val sydney = Feature(Point(LatLng(coord.y, coord.x)), properties=Some(Json.obj("name" -> "Sydney")))
    val json = Json.toJson(sydney)

    println(json)

    val feature = Json.fromJson[Feature[LatLng]](json)
    println(feature.get)

    val p = Json.fromJson[Point[LatLng]](json \ "geometry").get
    val pString = "POINT(" + p.coordinates.lng.toString + " " + p.coordinates.lat.toString + ")"
    println(pString)

    val pHex = WKBWriter.toHex(wkbWriter.write(wktReader.read(pString)))
    println(pHex)
  }
}
