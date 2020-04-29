package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.icu.util.ValueIterator
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.algo.Point
import com.example.androidapplication.R

private const val STROKE_WIDTH = 38f
class MyCanvasView(context: Context) : View(context) {
    public var listOfListOfPoints = Array<ArrayList<Point>>(100) {i -> arrayListOf()}
    private var currentNumberOfPoints = 0
    private var currentNumberOfLists = 0
    private var path = Path()
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)

    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }


    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop


    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f


    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove()
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }


    private fun touchStart() {
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentNumberOfLists++
        currentNumberOfPoints = 1
        listOfListOfPoints[currentNumberOfLists].add(Point(motionTouchEventX.toDouble(), motionTouchEventY.toDouble()))
    }

    private fun touchMove() {
        val p = listOfListOfPoints[currentNumberOfLists].get(currentNumberOfPoints - 1)
        val dx = Math.abs(motionTouchEventX - p.x.toFloat())
        val dy = Math.abs(motionTouchEventY - p.y.toFloat())
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(p.x.toFloat(), p.y.toFloat(), (motionTouchEventX + p.x.toFloat()) / 2, (motionTouchEventY + p.y.toFloat()) / 2)

            currentNumberOfPoints++
            listOfListOfPoints[currentNumberOfLists].add(Point(motionTouchEventX.toDouble(), motionTouchEventY.toDouble()))
            invalidate()

        }
    }

    private fun touchUp() {
        invalidate()
    }
}

