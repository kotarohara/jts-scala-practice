package com.example

import com.vividsolutions.jts.geom.{Point, LineString}
import com.vividsolutions.jts.io.{WKTWriter, WKTReader, WKBWriter, WKBReader}

// Example 1: Reading/writing WKB and WKB

// Great examples in https://github.com/tminglei/slick-pg/blob/5c2ad79b5cbcf0bb6443af59aa56983ee22e90d8/src/test/scala/com/github/tminglei/slickpg/addon/PgPostGISSupportSuite.scala
object WktAndWkb {
  // WKT == Well-Known Text, WKB == Well-Known Binary.
  // https://en.wikipedia.org/wiki/Well-known_text
  val wktReader = new WKTReader()
  val wktWriter = new WKTWriter()
  val wkbReader = new WKBReader()
  val wkbWriter = new WKBWriter(2, true)


  def main(args: Array[String]): Unit = {
    val p = "POINT(-71.064544 42.28787)"
    val point = wktReader.read(p).asInstanceOf[Point]
    println(point.toString)

    val line = wktReader.read("LINESTRING(-10 0, 50 50, -100 -100, 10 -70, -10 0)").asInstanceOf[LineString]
    println(line.toString)

    val wkb = wkbWriter.write(point)
    println(wkb)
    println(wkbReader.read(wkb).equals(point))

    val POLYGON_HEX = "01030000000100000005000000000000000000000000000000000000000000000000000000000000000000F03F000000000000F03F000000000000F03F000000000000F03F000000000000000000000000000000000000000000000000"
    // hexToBytes: http://tsusiatsoftware.net/jts/javadoc/com/vividsolutions/jts/io/WKBReader.html#hexToBytes(java.lang.String)
    val POLYGON_BYTE = WKBReader.hexToBytes(POLYGON_HEX)
    val poly = wkbReader.read(POLYGON_BYTE)
    println(poly)


    val hex = WKBWriter.toHex(wkbWriter.write(point))
    println(hex)
  }
}
