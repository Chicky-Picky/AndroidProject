package com.example.androidapplication

import com.example.algo.Point

object Points {
    var shapes = ArrayList<ArrayList<Point>>()
    var shapeType = ArrayList<String>()

    fun redef(x: ArrayList<Point>, s: String) {
        shapes.add(x)
        shapeType.add(s)
    }
}

