package com.example.algo

import kotlin.math.absoluteValue
import kotlin.math.sign

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
            var ab: Point
            var bc: Point
            var product: Double
            var productPrev = 0.0
            var area2 = 0.0
            val pointsRounded : MutableList<Point> = mutableListOf()
            var num : Long

            for (i in points.indices) {
                pointsRounded.add(points[i])
            }

            for (i in pointsRounded.indices) {
                num = (pointsRounded[i].x.absoluteValue * 100.0).toLong()

                /* checking the last digit and rounding */
                if (num % 10 < 5)
                    pointsRounded[i].x = sign(pointsRounded[i].x) *
                            (num - (num % 10)) / 100.0
                else
                    pointsRounded[i].x = sign(pointsRounded[i].x) *
                            (num - (num % 10) + 10) / 100.0

                /* the y-coordinate as an integer without sign */
                num = (pointsRounded[i].y.absoluteValue * 100.0).toLong()

                /* checking the last digit and rounding */
                if (num % 10 < 5)
                    pointsRounded[i].y = sign(pointsRounded[i].y) *
                            (num - (num % 10)) / 100.0
                else
                    pointsRounded[i].y = sign(pointsRounded[i].y) *
                            (num - (num % 10) + 10) / 100.0
            }

            /* checking the convexity of the rounded polygon */
            for (i in pointsRounded.indices) {
                if (i == 0) {
                    ab = Point(
                        pointsRounded[i].x - pointsRounded[pointsRounded.size - 1].x,
                        pointsRounded[i].y - pointsRounded[pointsRounded.size - 1].y)

                    bc = Point(
                        pointsRounded[i + 1].x - pointsRounded[i].x,
                        pointsRounded[i + 1].y - pointsRounded[i].y)

                    productPrev = ab.x * bc.y - ab.y * bc.x
                    area2 += (points[i].x + points[i + 1].x) * (points[i + 1].y - points[i].y)
                    continue
                }
                if (i == pointsRounded.size - 1) {
                    ab = Point(
                        pointsRounded[i].x - pointsRounded[i - 1].x,
                        pointsRounded[i].y - pointsRounded[i - 1].y)

                    bc = Point(
                        pointsRounded[0].x - pointsRounded[i].x,
                        pointsRounded[0].y - pointsRounded[i].y)

                    area2 += (points[i].x + points[0].x) * (points[0].y - points[i].y)
                }
                else {
                    ab = Point(
                        pointsRounded[i].x - pointsRounded[i - 1].x,
                        pointsRounded[i].y - pointsRounded[i - 1].y
                    )

                    bc = Point(
                        pointsRounded[i + 1].x - pointsRounded[i].x,
                        pointsRounded[i + 1].y - pointsRounded[i].y
                    )

                    area2 += (points[i].x + points[i + 1].x) * (points[i + 1].y - points[i].y)
                }

                product = ab.x * bc.y - ab.y * bc.x

                if (productPrev * product < 0)
                  return Undefined

                productPrev = product
            }

            /* search for the center of gravity of a polygon */
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

            /* counting small and large half axes */
            val leftB = x.min() ?: 0.0
            val rightB = x.max() ?: 0.0
            val bottomA = y.min() ?: 0.0
            val topA = y.max() ?: 0.0

            return Ellipse(
                Point(cx - (rightB - leftB) / 2, cy), Point(cx, cy + (topA - bottomA) / 2),
                Point(cx + (rightB - leftB) / 2, cy), Point(cx, cy - (topA - bottomA) / 2))
        }
    }
}