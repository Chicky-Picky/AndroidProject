package com.example.algo

import org.junit.Test
import org.junit.Assert.*
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class CreateShapeTests {
    @Test
    fun pointsOnLineSegment() {
        val segment = LineSegment(Point(0.0, -3.0), Point(19.0, 149.0))
        val points: List<Point> = listOf(Point(0.0, -3.0), Point(3.0, 21.0),
            Point(5.0, 37.0), Point(7.0, 53.0), Point(10.0, 77.0),
            Point(13.0, 101.0), Point(15.0, 117.0), Point(19.0, 149.0))

        assertEquals(segment, VectorizationImpl().vectorize(points))
    }

    @Test
    fun pointsNotOnLineSegment() {
        val segment = LineSegment(Point(0.0, -2.9947646551724176),
            Point(19.0, 148.9912801724138))
        val points: List<Point> = listOf(Point(0.0, -3.015), Point(3.0, 21.0),
            Point(5.0, 37.02), Point(7.0, 53.034), Point(10.0, 77.0),
            Point(13.0, 100.95), Point(15.0, 117.0), Point(19.0, 149.0))

        assertEquals(segment, VectorizationImpl().vectorize(points))
    }

    @Test
    fun notLineSegment() {
        val points: List<Point> = listOf(Point(0.0, -3.45), Point(1.0, 5.85),
            Point(2.0, 12.2), Point(3.0, 18.5), Point(4.0, 29.95),
            Point(5.0, 35.7), Point(6.0, 46.4), Point(7.0, 53.4),
            Point(8.0, 61.6), Point(9.0, 69.7), Point(10.0, 74.75),
            Point(11.0, 84.75), Point(12.0, 94.55), Point(13.0, 99.85),
            Point(14.0, 109.55), Point(15.0, 119.05), Point(16.0, 127.25),
            Point(17.0, 132.6), Point(18.0, 139.85), Point(19.0, 148.3))

        assertEquals(Undefined, VectorizationImpl().vectorize(points))
    }

    @Test
    fun pointsOnCircle() {
        val ellipse = Ellipse(
            Point(-1.0, 0.0), Point(0.0, 1.0),
            Point(1.0, 0.0), Point(0.0, -1.0))
        val points: List<Point> = listOf(
            Point(0.0, 1.0), Point(-1.0, 0.0),
            Point(0.0, -1.0), Point(1.0, 0.0))

        assertEquals(ellipse, VectorizationImpl().vectorize(points))
    }

    @Test
    fun pointsOnEllipse() {
        val ellipse = Ellipse(
            Point(-3.0, 0.0), Point(0.0, 2.0),
            Point(3.0, 0.0), Point(0.0, -2.0))
        val points: List<Point> = listOf(
            Point(-3.0, 0.0), Point(0.0, 2.0),
            Point(3.0, 0.0), Point(0.0, -2.0))

        assertEquals(ellipse, VectorizationImpl().vectorize(points))
    }

    @Test
    fun pointsNotOnEllipse() {
        val ellipse = Ellipse(
            Point(-3.4126744186046514, -0.014922480620155096),
            Point(0.2023255813953487, 1.985077519379845),
            Point(3.817325581395349, -0.014922480620155096),
            Point(0.2023255813953487, -2.014922480620155))
        val points: List<Point> = listOf(
            Point(-3.23, 0.0), Point(-1.57, 1.46),
            Point(0.0, 2.0), Point(1.52, 1.53),
            Point(4.0, 0.0), Point(1.49, -1.55),
            Point(0.0, -2.0), Point(-1.57, -1.46))

        assertEquals(ellipse, VectorizationImpl().vectorize(points))
    }

    @Test
    fun notEllipse() {
        val points: List<Point> = listOf(
            Point(-3.0, 0.0), Point(4.0, 2.0),
            Point(0.0, 2.0), Point(0.0, -2.0),
            Point(-3.0, 0.0))

        assertEquals(Undefined, VectorizationImpl().vectorize(points))
    }

    @Test
    fun emptySet() {
        val segment = LineSegment(Point(0.0, 0.0), Point(0.0, 0.0))
        val points: List<Point> = listOf()

        assertEquals(segment, VectorizationImpl().vectorize(points))
    }
}