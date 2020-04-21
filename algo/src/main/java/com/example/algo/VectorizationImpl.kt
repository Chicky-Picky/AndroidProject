package com.example.algo

class VectorizationImpl : Vectorization {
    override fun vectorize(points: List<Point>): BaseShape {
        var sumx = 0.0
        var sumy = 0.0
        var sumx2 = 0.0
        var sumxy = 0.0
        val a: Double
        val b: Double
        var err = 0.0
        val x: MutableList<Double> = mutableListOf()

        if (points.isEmpty())
            return LineSegment(Point(0.0, 0.0), Point(0.0, 0.0))

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
            LineSegment(x.min()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0),
                x.max()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0))
        else
            Undefined
    }
}