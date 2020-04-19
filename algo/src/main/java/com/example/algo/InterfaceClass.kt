package com.example.algo

interface BaseShape

data class LineSegment(val point1: Point, val point2: Point) : BaseShape

interface Vectorization {
    fun vectorize(points: List<Point>) : BaseShape
}