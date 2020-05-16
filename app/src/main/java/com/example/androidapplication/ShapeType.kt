package com.example.androidapplication

import android.content.Context
import android.widget.Toast
import com.example.algo.*


class ShapeType(private var points: Points) : ShapeVisitor {

    private fun redef(x: ArrayList<Point>, s: String) {
        points.shapes.add(x)
        points.shapeType.add(s)
    }

    override fun visit(undefined: Undefined) {
        val x = ArrayList<Point>()
        val s = "undefine"
        redef(x, s)

    }

    override fun visit(lineSegment: LineSegment) {
        val x = ArrayList<Point>()
        val s = "lineSegment"
        x.add(Point(lineSegment.point1.x, lineSegment.point1.y))
        x.add(Point(lineSegment.point2.x, lineSegment.point2.y))
        redef(x, s)
    }

    override fun visit(ellipse: Ellipse) {
        val x = ArrayList<Point>()
        val s = "ellipse"
        x.add(ellipse.left)
        x.add(ellipse.top)
        x.add(ellipse.right)
        x.add(ellipse.bottom)
        TempClass.lineDebug.add(0)
        TempClass.lineDebug.add(0)
        redef(x, s)
    }



}