package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.algo.*
import com.example.algo.Point
import com.example.androidapplication.ShapeType
import com.example.algo.Points
import kotlin.math.abs
import kotlin.math.sqrt


class MyCanvasView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : View(context, attrs, defStyle) {


    private var listOfListOfPoints = ArrayList<ArrayList<Point>>()
    private var currentNumberOfPoints = 0
    private var currentNumberOfLists = -1
    private var path = Path()
    private var vector = VectorizationImpl()
    private var lineSegmentsEnds = ArrayList<Point>()
    private var points = Points(arrayListOf(), arrayListOf())
    public var mode: Int = 0


    private val paint = Paint().apply {
        color = Color.LTGRAY
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 10f
    }

    private val paint1 = Paint().apply {
        color = Color.BLACK
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 15f
    }

    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop

    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f


    private fun dist(x: Point, y: Point): Double{
        val z = Point(x.x - y.x, x.y - y.y)
        return sqrt(z.x * z.x + z.y * z.y)
    }

    private fun distCheck (x: ArrayList<Point>): ArrayList<Point>{
        val y: ArrayList<Point> = arrayListOf(Point(0.0, 0.0), Point(0.0, 0.0))
        var d = 30.0
        for (i in 0 until lineSegmentsEnds.size)
        {
            val z = lineSegmentsEnds[i]
            if (dist(x[0], z) < d)
            {
                y[0] = z
                d = dist(x[0], z)
            }
        }

        d = 30.0
        for (i in 0 until lineSegmentsEnds.size)
        {
            val z = lineSegmentsEnds[i]
            if (dist(x[1], z) < d)
            {
                y[1] = z
                d = dist(x[1], z)
            }
        }
        if (y[0] == Point(0.0, 0.0))
        {
            lineSegmentsEnds.add(x[0])
            y[0] = x[0]
        }
        if (y[1] == Point(0.0, 0.0))
        {
            lineSegmentsEnds.add(x[1])
            y[1] = x[1]
        }
        return y
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


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas) {

        if (mode == 0) {
            canvas.drawPath(path, paint)
        }
        for (i in 0 until points.shapes.size)
        {
            if (points.shapeType[i] == "undefine" && i == points.shapes.size - 1)
            {
                val text = "Could not determine shape"
                val duration = Toast.LENGTH_SHORT

                Toast.makeText(context, text, duration).show()
                points.shapeType[i] = "undefined"
            }
            if (points.shapeType[i] == "lineSegment")
            {
                canvas.drawLine(points.shapes[i][0].x.toFloat(), points.shapes[i][0].y.toFloat(), points.shapes[i][1].x.toFloat(), points.shapes[i][1].y.toFloat(), paint1)

                points.shapes[i] = distCheck(points.shapes[i])

                canvas.drawLine(points.shapes[i][0].x.toFloat(), points.shapes[i][0].y.toFloat(), points.shapes[i][1].x.toFloat(), points.shapes[i][1].y.toFloat(), paint1)


            }
            if (points.shapeType[i] == "ellipse")
            {
                canvas.drawOval(points.shapes[i][0].x.toFloat(), points.shapes[i][1].y.toFloat(), points.shapes[i][2].x.toFloat(), points.shapes[i][3].y.toFloat(), paint1)
            }
        }

    }


    private fun touchStart() {
        path.moveTo(motionTouchEventX, motionTouchEventY)
        listOfListOfPoints.add(arrayListOf())
        currentNumberOfLists++
        currentNumberOfPoints = 1
        listOfListOfPoints[currentNumberOfLists].add(Point(motionTouchEventX.toDouble(), motionTouchEventY.toDouble()))
    }

    private fun touchMove() {
        val p = listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints - 1]
        val dx = abs(motionTouchEventX - p.x.toFloat())
        val dy = abs(motionTouchEventY - p.y.toFloat())
        if (dx >= touchTolerance || dy >= touchTolerance) {
            invalidate()
            path.quadTo(p.x.toFloat(), p.y.toFloat(), (motionTouchEventX + p.x.toFloat()) / 2, (motionTouchEventY + p.y.toFloat()) / 2)

            currentNumberOfPoints++
            listOfListOfPoints[currentNumberOfLists].add(Point(motionTouchEventX.toDouble(), motionTouchEventY.toDouble()))

        }
    }

    private fun touchUp() {
        val p = listOfListOfPoints[currentNumberOfLists][currentNumberOfPoints - 1]
        path.quadTo(p.x.toFloat(), p.y.toFloat(), (motionTouchEventX + p.x.toFloat()) / 2, (motionTouchEventY + p.y.toFloat()) / 2)

        currentNumberOfPoints++
        listOfListOfPoints[currentNumberOfLists].add(Point(motionTouchEventX.toDouble(), motionTouchEventY.toDouble()))

        vector.vectorize(listOfListOfPoints[currentNumberOfLists]).accept(ShapeType(points))
        invalidate()
    }





}

