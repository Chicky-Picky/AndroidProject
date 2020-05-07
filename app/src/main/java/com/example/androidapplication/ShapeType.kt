package com.example.androidapplication

import android.graphics.Canvas
import com.example.algo.LineSegment
import com.example.algo.ShapeVisitor
import com.example.algo.Undefined



class ShapeType : ShapeVisitor {
    override fun visit(undefined: Undefined) {
        //Relaxxx
    }

    override fun visit(lineSegment: LineSegment) {
        LinePoints.redef(lineSegment.point1.x, lineSegment.point1.y, lineSegment.point2.x, lineSegment.point2.y)
    }

}