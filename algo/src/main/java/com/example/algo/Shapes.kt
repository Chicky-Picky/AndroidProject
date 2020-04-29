package com.example.algo

interface BaseShape {
    fun accept(visitor: ShapeVisitor)
}

object Undefined: BaseShape {
    override fun accept(visitor: ShapeVisitor) = visitor.visit(this)
}

data class LineSegment(val point1: Point, val point2: Point) : BaseShape {
    override fun accept(visitor: ShapeVisitor) = visitor.visit(this)
}