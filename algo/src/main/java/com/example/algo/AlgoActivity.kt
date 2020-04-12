package com.example.algo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AlgoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_algo)
    }
}

data class Point(val x: Double, val y: Double)

fun createLineSegment(points: List<Point>) : List<Point> {
    var sumx = 0.0
    var sumy = 0.0
    var sumx2 = 0.0
    var sumxy = 0.0
    val a: Double
    val b: Double
    var err = 0.0
    val x: MutableList<Double> = mutableListOf()

    if (points.isEmpty())
        return listOf(Point(0.0, 0.0), Point(0.0, 0.0))

    for (i in points.indices) {
        sumx += points[i].x
        sumy += points[i].y
        sumx2 += points[i].x * points[i].x
        sumxy += points[i].x * points[i].y
    }

    a = (points.size * sumxy - (sumx * sumy)) / (points.size * sumx2 - sumx * sumx)
    b = (sumy - a * sumx) / points.size

    points.forEach {
        x.add(it.x)
    }

    for (i in points.indices)
        err += (points[i].y - a * points[i].x - b) * (points[i].y - a * points[i].x - b)

    return if (err < 1)
        listOf(x.min()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0),
            x.max()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0))
    else
        points
}