package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.algo.*
import com.example.algo.Point
import com.example.androidapplication.R
import com.example.androidapplication.ShapeType
import com.example.algo.Points
import kotlin.math.abs
import kotlin.math.sqrt


private const val STROKE_WIDTH = 38f
class MyCanvasView(context: Context) : View(context) {
    private var listOfListOfPoints = ArrayList<ArrayList<Point>>()
    private var currentNumberOfPoints = 0
    private var currentNumberOfLists = -1
    private var path = Path()
    private val drawColor = ResourcesCompat.getColor(resources, R.color.colorPaint, null)
    private var vector = VectorizationImpl()
    private var lineSegmentsEnds = ArrayList<Point>()
    var points = Points(arrayListOf(), arrayListOf())




    private val paint = Paint().apply {
        color = drawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 38f
    }

    private val paint2 = Paint().apply {
        color = Color.RED
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 19f
    }

    private val paint3 = Paint().apply {
        color = Color.GREEN
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 19f
    }


    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop


    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f


    fun dist(x: Point, y: Point): Double{
        val z = Point(x.x - y.x, x.y - y.y)
        return sqrt(z.x * z.x + z.y * z.y)
    }


    fun distCheck (x: ArrayList<Point>): ArrayList<Point>{
        var y: ArrayList<Point> = arrayListOf(Point(0.0, 0.0), Point(0.0, 0.0))
        var d = 138.0
        for (i in 0 until lineSegmentsEnds.size)
        {
            val z = lineSegmentsEnds[i]
            if (dist(x[0], z) < d)
            {
                y[0] = z
                d = dist(x[0], z)
            }
        }

        d = 138.0
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

        canvas.drawPath(path, paint)
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
                canvas.drawLine(points.shapes[i][0].x.toFloat(), points.shapes[i][0].y.toFloat(), points.shapes[i][1].x.toFloat(), points.shapes[i][1].y.toFloat(), paint2)

                points.shapes[i] = distCheck(points.shapes[i])

                canvas.drawLine(points.shapes[i][0].x.toFloat(), points.shapes[i][0].y.toFloat(), points.shapes[i][1].x.toFloat(), points.shapes[i][1].y.toFloat(), paint3)


            }
            if (points.shapeType[i] == "ellipse")
            {
                canvas.drawOval(points.shapes[i][0].x.toFloat(), points.shapes[i][1].y.toFloat(), points.shapes[i][2].x.toFloat(), points.shapes[i][3].y.toFloat(), paint2)
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

