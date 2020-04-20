package com.example.algo

interface BaseShape

object Undefined: BaseShape

data class LineSegment(val point1: Point, val point2: Point) : BaseShape