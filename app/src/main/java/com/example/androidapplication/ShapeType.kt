package com.example.androidapplication

import com.example.algo.LineSegment
import com.example.algo.Point
import com.example.algo.ShapeVisitor
import com.example.algo.Undefined



class ShapeType : ShapeVisitor {
    override fun visit(undefined: Undefined) {
        //Relaxxx
    }

    override fun visit(lineSegment: LineSegment) {
        val x = ArrayList<Point>()
        val s = "lineSegment"
        x.add(Point(lineSegment.point1.x, lineSegment.point1.y))
        x.add(Point(lineSegment.point2.x, lineSegment.point2.y))
        Points.redef(x, s)
    }

}