package com.example.androidapplication

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.ViewConfiguration
import androidx.lifecycle.ViewModel
import com.example.algo.Point
import com.example.algo.Points
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.algo.VectorizationImpl
import kotlin.math.abs
import kotlin.math.sqrt

class MyViewModel(context: Context) : ViewModel() {

    /*private val liveLists = MutableLiveData<ArrayList<ArrayList<Point>>>()

    fun getLists(): LiveData<ArrayList<ArrayList<Point>>> {
        return liveLists
    }


    private var listOfListOfPoints = ArrayList<ArrayList<Point>>()
    private var currentNumberOfPoints = 0
    private var currentNumberOfLists = -1
    private var path = Path()
    private var vector = VectorizationImpl()
    private var lineSegmentsEnds = ArrayList<Point>()
    private var lineSegmentsIndexes = ArrayList<Int>()
    var points = Points(arrayListOf(), arrayListOf())
    var mode: Int = 0



    //var model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)

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
        var d = 20.0
        for (i in 0 until lineSegmentsEnds.size)
        {
            val z = lineSegmentsEnds[i]
            if (dist(x[0], z) < d)
            {
                y[0] = z
                d = dist(x[0], z)
            }
        }

        d = 20.0
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
            lineSegmentsIndexes.add(1)
            y[0] = x[0]
        }
        else
        {
            lineSegmentsIndexes.add(0)
        }
        if (y[1] == Point(0.0, 0.0))
        {
            lineSegmentsEnds.add(x[1])
            lineSegmentsIndexes.add(1)
            y[1] = x[1]
        }
        else
        {
            lineSegmentsIndexes.add(0)
        }
        return y
    }






    private fun touchStart() {
        //listOfListOfPoints = getLists()


        //path.reset()
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
            //invalidate()
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
        //invalidate()

    }





















    /*
    private var listsOfPoints = ArrayList<ArrayList<Point>>()
    private var currentPoints = 0
    private var currentLists = -1
    private var lineSegments = ArrayList<Point>()
    private var shapes = Points(arrayListOf(), arrayListOf())
    //public var mode: Int = 0

    var listOfListOfPoints : MutableLiveData<ArrayList<ArrayList<Point>>>? = null
    var mode: MutableLiveData<Int>? = null

    fun getMode() : LiveData<Int> {
        if (mode == null) {
            mode = MutableLiveData()
        }
        return mode as MutableLiveData<Int>
    }
    */






    /*
    fun getListOfListOfPoints(): LiveData<ArrayList<ArrayList<Point>>> {
        if (listOfListOfPoints == null) {
            listOfListOfPoints = MutableLiveData()
            loadListOfListOfPoints()
        }
        return listOfListOfPoints as MutableLiveData<ArrayList<ArrayList<Point>>>

    }

    private fun loadListOfListOfPoints() {
    }
    */

    /*fun getLists(): ArrayList<ArrayList<Point>> {
        return listsOfPoints
    }

    private fun loadLists(a: ArrayList<ArrayList<Point>>) {
        listsOfPoints = a
    }*/

     */
}