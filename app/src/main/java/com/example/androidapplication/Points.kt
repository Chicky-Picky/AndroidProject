package com.example.androidapplication

import com.example.algo.Point

object Points {
    var coordinates = mutableListOf<Point>()
}

object LinePoints {
    var coordinates = mutableListOf<Float>(83.83f, 83.83f, 38.38f, 38.38f)

    fun redef(a: Double, b: Double, c: Double, d: Double  ) {
        coordinates = mutableListOf(a.toFloat(), b.toFloat(), c.toFloat(), d.toFloat())
    }
}