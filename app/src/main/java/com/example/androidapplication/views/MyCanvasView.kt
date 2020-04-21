package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.algo.Point
import com.example.androidapplication.R

private const val STROKE_WIDTH = 12f
class MyCanvasView(context: Context) : View(context) {
    //public var listOfListOfPoints = Array<Array<Point>>(100, {i -> Array(10000, {i -> com.example.algo.Point(0.0, 0.0)})})
    public var listOfListOfPoints = Array<Array<Point>>(100) { i -> Array(10000) { i -> com.example.algo.Point(0.0, 0.0)} }
    private var currentNumberOfPoints = 0
    private var currentNumberOfLists = 0
    private var path = Path()

    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = STROKE_WIDTH
    }

    private val paint2 = Paint().apply {
        color = Color.RED
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

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(backgroundColor)

    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(extraBitmap, 0f, 0f, null)
    }

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


    private fun touchStart() {
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentNumberOfLists++
        currentNumberOfPoints = 1
        listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].x = motionTouchEventX.toDouble()
        listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].y = motionTouchEventY.toDouble()
    }

    private fun touchMove() {
        val px = listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].x
        val py = listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].y
        val dx = Math.abs(motionTouchEventX - px)
        val dy = Math.abs(motionTouchEventY - py)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(px.toFloat(), py.toFloat(), (motionTouchEventX + px.toFloat()) / 2, (motionTouchEventY + py.toFloat()) / 2)

            currentNumberOfPoints++
            listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].x = motionTouchEventX.toDouble()
            listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints].y = motionTouchEventY.toDouble()
            extraCanvas.drawPath(path, paint)
        }
        invalidate()
    }

    private fun touchUp() {
        path.reset()
        for (i in 1 until listOfListOfPoints[currentNumberOfLists].size){
            val px = listOfListOfPoints[currentNumberOfLists][i].x
            val py = listOfListOfPoints[currentNumberOfLists][i].y
            extraCanvas.drawCircle(px.toFloat(), py.toFloat(), 2f, paint2)
            }
    }
}

