package com.example.androidapplication

import com.example.algo.Point

object Points {
    var shapes = ArrayList<ArrayList<Point>>()

    fun redef(x: ArrayList<Point>) {
        shapes.add(x)
    }
}

