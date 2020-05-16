package com.example.androidapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.algo.Points
import com.example.androidapplication.views.MyCanvasView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView =
            MyCanvasView(this)
        //setContentView(myCanvasView)
        setContentView(R.layout.activity_main)

        var model: MyViewModel = ViewModelProviders.of(this).get(
            MyViewModel::class.java)
        //var listOfListOfPoints = model.getListOfListOfPoints()
    }




    fun changeMode(view: View) {
        TempClass.mode = -TempClass.mode + 1
        textView.text = TempClass.mode.toString()
        myView.invalidate()
    }

    fun clearView(view: View) {
        TempClass.listsOfPoints.clear()
        TempClass.currentPoints = 0
        TempClass.currentLists = -1
        TempClass.lineSegments.clear()
        TempClass.shapes = Points(arrayListOf(), arrayListOf())
        TempClass.path.reset()
        myView.invalidate()
    }

    fun undoView(view: View) {

        TempClass.listsOfPoints[TempClass.currentLists].clear()
        if (TempClass.lineDebug[TempClass.lineDebug.size - 1] == 1)
        {
            TempClass.lineSegments.removeAt(TempClass.lineSegments.size - 1)
        }
        TempClass.lineDebug.removeAt(TempClass.lineDebug.size - 1)
        if (TempClass.lineDebug[TempClass.lineDebug.size - 1] == 1)
        {
            TempClass.lineSegments.removeAt(TempClass.lineSegments.size - 1)
        }
        TempClass.lineDebug.removeAt(TempClass.lineDebug.size - 1)
        TempClass.shapes.shapes.removeAt(TempClass.shapes.shapes.size - 1)
        TempClass.shapes.shapeType.removeAt(TempClass.shapes.shapeType.size - 1)
        TempClass.currentPoints--
        TempClass.currentLists--
        myView.invalidate()
    }


}