package com.example.algo

import org.junit.Test
import org.junit.Assert.*
import kotlin.math.*

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
        val segment = LineSegment(Point(-0.0025276567064346778, -3.015),
            Point(19.0010878637486, 149.0))
        val points: List<Point> = listOf(Point(0.0, -3.015), Point(3.0, 21.0),
            Point(5.0, 37.02), Point(7.0, 53.034), Point(10.0, 77.0),
            Point(13.0, 100.95), Point(15.0, 117.0), Point(19.0, 149.0))

        assertEquals(segment, VectorizationImpl().vectorize(points))
    }

    @Test
    fun pointsOnCircle() {
        val points: MutableList<Point> = mutableListOf()

        for (i in 0..9999)
            points.add(Point(100 * cos(i * 2 * PI / 10000),
                100 * sin(i * 2 * PI / 10000)))

        val aExp = 100.0
        val bExp = 100.0
        val cxExp = 0.0
        val cyExp = 0.0
        var aRes = 0.0
        var bRes = 0.0
        var cxRes = 0.0
        var cyRes = 0.0

        VectorizationImpl().vectorize(points).accept(object : ShapeVisitor {
            override fun visit(undefined: Undefined) {
            }

            override fun visit(lineSegment: LineSegment) {
            }

            override fun visit(ellipse: Ellipse) {
                cxRes = ellipse.top.x
                cyRes = ellipse.left.y
                aRes = cxRes - ellipse.left.x
                bRes = cyRes - ellipse.bottom.y
            }
        })

        assertFalse(aRes == 0.0)
        assertTrue((aExp - aRes).absoluteValue < 0.001)
        assertTrue((bExp - bRes).absoluteValue < 0.001)
        assertTrue((cxExp - cxRes).absoluteValue < 0.001)
        assertTrue((cyExp - cyRes).absoluteValue < 0.001)
    }

    @Test
    fun pointsOnEllipse() {
        val points: MutableList<Point> = mutableListOf()

        for (i in 0..9999)
            points.add(Point(50 + 300 * cos(i * 2 * PI / 10000),
                30 + 200 * sin(i * 2 * PI / 10000)))

        val aExp = 300.0
        val bExp = 200.0
        val cxExp = 50.0
        val cyExp = 30.0
        var aRes = 0.0
        var bRes = 0.0
        var cxRes = 0.0
        var cyRes = 0.0

        VectorizationImpl().vectorize(points).accept(object : ShapeVisitor {
            override fun visit(undefined: Undefined) {
            }

            override fun visit(lineSegment: LineSegment) {
            }

            override fun visit(ellipse: Ellipse) {
                cxRes = ellipse.top.x
                cyRes = ellipse.left.y
                aRes = cxRes - ellipse.left.x
                bRes = cyRes - ellipse.bottom.y
            }
        })

        assertFalse(aRes == 0.0)
        assertTrue((aExp - aRes).absoluteValue < 0.001)
        assertTrue((bExp - bRes).absoluteValue < 0.001)
        assertTrue((cxExp - cxRes).absoluteValue < 0.001)
        assertTrue((cyExp - cyRes).absoluteValue < 0.001)
    }

    @Test
    fun pointsNotOnEllipse() {
        val points: MutableList<Point> = mutableListOf()

        for (i in 0..9999)
            points.add(Point(100 + 300 * cos(i * 2 * PI / 10000) +
                    (i % 7) * (-1.0).pow(i) * 0.05,
                150 + 200 * sin(i * 2 * PI / 10000) + (i % 13) * (-1.0).pow(i + 1) * 0.07))

        val aExp = 300.0
        val bExp = 200.0
        val cxExp = 100.0
        val cyExp = 150.0
        var aRes = 0.0
        var bRes = 0.0
        var cxRes = 0.0
        var cyRes = 0.0

        VectorizationImpl().vectorize(points).accept(object : ShapeVisitor {
            override fun visit(undefined: Undefined) {
            }

            override fun visit(lineSegment: LineSegment) {
            }

            override fun visit(ellipse: Ellipse) {
                cxRes = ellipse.top.x
                cyRes = ellipse.left.y
                aRes = cxRes - ellipse.left.x
                bRes = cyRes - ellipse.bottom.y
            }
        })

        assertFalse(aRes == 0.0)
        assertTrue((aExp - aRes).absoluteValue < 1)
        assertTrue((bExp - bRes).absoluteValue < 1)
        assertTrue((cxExp - cxRes).absoluteValue < 1)
        assertTrue((cyExp - cyRes).absoluteValue < 1)
    }

    @Test
    fun undefined() {
        val points: List<Point> = listOf(Point(0.0, -3.45), Point(10.0, 5.85),
            Point(-20.0, 12.2), Point(30.0, 18.5), Point(40.0, 29.95),
            Point(50.0, 35.7), Point(-60.0, 46.4), Point(-70.0, 53.4),
            Point(-80.0, 61.6), Point(90.0, 69.7), Point(100.0, 74.75))

        assertEquals(Undefined, VectorizationImpl().vectorize(points))
    }

    @Test
    fun emptySet() {
        val segment = LineSegment(Point(0.0, 0.0), Point(0.0, 0.0))
        val points: List<Point> = listOf()

        assertEquals(segment, VectorizationImpl().vectorize(points))
    }
}