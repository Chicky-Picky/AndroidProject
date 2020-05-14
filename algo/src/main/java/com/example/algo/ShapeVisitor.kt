package com.example.algo


interface ShapeVisitor {
    var points: Points
    fun redef(x: ArrayList<Point>, s: String)
    fun visit(undefined: Undefined)
    fun visit(lineSegment: LineSegment)
    fun visit(ellipse: Ellipse)
}