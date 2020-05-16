package com.example.androidapplication

import android.graphics.Path
import com.example.algo.Point
import com.example.algo.Points

object TempClass {
    var listsOfPoints = ArrayList<ArrayList<Point>>()
    var currentPoints = 0
    var currentLists = -1
    var path = Path()
    var lineSegments = ArrayList<Point>()
    var lineDebug = ArrayList<Int>()
    var shapes = Points(arrayListOf(), arrayListOf())
    var mode: Int = 0
}