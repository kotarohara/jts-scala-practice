package com.example

import com.vividsolutions.jts.geom.{Coordinate, Envelope}
import com.vividsolutions.jts.index.strtree._

object STRTreeExample {

  // https://github.com/metteo/jts/blob/master/jts-core/src/test/java/com/vividsolutions/jts/index/STRtreeTest.java
  def main(args: Array[String]): Unit = {
    val tolerance = 0.0
    val tree: STRtree = new STRtree()
    tree.insert(new Envelope(0, 10, 0, 10), "1")
    tree.insert(new Envelope(5, 15, 5, 15), "2")
    tree.insert(new Envelope(10, 20, 10, 20), "3")
    tree.insert(new Envelope(15, 25, 15, 25), "4")

  }
}
