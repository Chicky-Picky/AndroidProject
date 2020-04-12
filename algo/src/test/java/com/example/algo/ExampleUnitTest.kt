package com.example.algo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

class CreateLineSegmentTests {
    @Test
    fun pointsOnLineSegment() {
        val segment:  List<Point> = listOf(Point(0.0, -3.0), Point(19.0, 149.0))
        val points: List<Point> = listOf(Point(0.0, -3.0), Point(3.0, 21.0),
            Point(5.0, 37.0), Point(7.0, 53.0), Point(10.0, 77.0),
            Point(13.0, 101.0), Point(15.0, 117.0), Point(19.0, 149.0))

        assertEquals(segment, createLineSegment(points))
    }

    @Test
    fun pointsNotOnLineSegment() {
        val segment:  List<Point> = listOf(Point(0.0, -2.9947646551724176),
            Point(19.0, 148.9912801724138))
        val points: List<Point> = listOf(Point(0.0, -3.015), Point(3.0, 21.0),
            Point(5.0, 37.02), Point(7.0, 53.034), Point(10.0, 77.0),
            Point(13.0, 100.95), Point(15.0, 117.0), Point(19.0, 149.0))

        assertEquals(segment, createLineSegment(points))
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

        assertEquals(points, createLineSegment(points))
    }

    @Test
    fun emptySet() {
        val segment: List<Point> = listOf(Point(0.0, 0.0), Point(0.0, 0.0))
        val points: List<Point> = listOf()

        assertEquals(segment, createLineSegment(points))
    }
}