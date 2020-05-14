package com.example.algo

import kotlin.math.PI
import kotlin.math.absoluteValue

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
        val y: MutableList<Double> = mutableListOf()

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
            y.add(it.y)
        }

        for (i in points.indices)
            err += (points[i].y - a * points[i].x - b) * (points[i].y - a * points[i].x - b)

        if (err < 10000)
            return LineSegment(
                x.min()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0),
                x.max()?.let { Point(it, a * it + b) } ?: Point(0.0, 0.0))
        else {
            var area2 = 0.0
            var inside = false

            /* checking if the endings are close */
            if ((points[0].x - points[points.size - 1].x) *
                (points[0].x - points[points.size - 1].x) +
                (points[0].y - points[points.size - 1].y) *
                (points[0].y - points[points.size - 1].y) > 100000)
                return Undefined

            /* counting signed area x 2 */
            for (i in points.indices) {
                area2 += when (i == points.size - 1) {
                    true -> (points[i].x + points[0].x) * (points[0].y - points[i].y)
                    false -> (points[i].x + points[i + 1].x) * (points[i + 1].y - points[i].y)
                }
            }

            /* searching for the center of gravity of polygon */
            var cx = 0.0
            var cy = 0.0

            for (i in points.indices) {
                if (i == points.size - 1) {
                    cx += (points[i].x + points[0].x) *
                            (points[i].x * points[0].y - points[i].y * points[0].x) / (3.0 * area2)
                    cy += (points[i].y + points[0].y) *
                            (points[i].x * points[0].y - points[i].y * points[0].x) / (3.0 * area2)
                }
                else {
                    cx += (points[i].x + points[i + 1].x) *
                            (points[i].x * points[i + 1].y - points[i].y * points[i + 1].x) /
                            (3.0 * area2)
                    cy += (points[i].y + points[i + 1].y) *
                            (points[i].x * points[i + 1].y - points[i].y * points[i + 1].x) /
                            (3.0 * area2)
                }
            }

            /* checking if the center of gravity is inside of polygon */
            var size = points.size - 1

            for (i in points.indices) {
                if ((points[i].y < cy && points[size].y >= cy ||
                            points[size].y < cy && points[i].y >= cy) &&
                    (points[i].x + (cy - points[i].y) / (points[size].y - points[i].y) *
                            (points[size].x - points[i].x) < cx))
                    inside = !inside
                size = i
            }

            if (inside.not())
                return Undefined

            /* counting small and large half axes */
            val leftB = x.min() ?: 0.0
            val rightB = x.max() ?: 0.0
            val bottomA = y.min() ?: 0.0
            val topA = y.max() ?: 0.0
            val A = (rightB - leftB) / 2
            val B = (topA - bottomA) / 2

            /* checking if area of recognised ellipse is close to area of polygon */
            if ((area2.absoluteValue / 2 - PI * A * B).absoluteValue > PI * A * B * 0.2)
                return Undefined

            return Ellipse(
                Point(cx + A, cy), Point(cx, cy - B),
                Point(cx - A, cy), Point(cx, cy + B)
            )
        }
    }
}