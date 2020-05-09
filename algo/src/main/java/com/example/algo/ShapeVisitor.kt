package com.example.algo

interface ShapeVisitor {
    fun visit(undefined: Undefined)
    fun visit(lineSegment: LineSegment)
    fun visit(ellipse: Ellipse)
}