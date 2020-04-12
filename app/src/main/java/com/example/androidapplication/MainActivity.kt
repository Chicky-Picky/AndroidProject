package com.example.androidapplication

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapplication.views.MyCanvasView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        setContentView(myCanvasView)

    }



}

/*class MainActivity : Activity(), View.OnTouchListener {



    var tv: TextView? = null
    var x = 0f
    var y = 0f
    var z = 0
    var circleNumber: String? = null
    
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv = TextView(this)
        tv!!.setOnTouchListener(this)
        setContentView(tv)
    }




    override fun onTouch(v: View, event: MotionEvent): Boolean {



         fun createCircle(canvas : Canvas) {


            val paint = Paint()
            paint.setColor(Color.BLACK)

            paint.setStyle(Paint.Style.FILL)
            canvas.drawCircle(x, y, 100F, paint)

        }
        createCircle(Canvas())
        x = event.x
        y = event.y
        z++

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                circleNumber = "Number of Circles: $z"
            }
            MotionEvent.ACTION_MOVE -> circleNumber = "Number of Circles: $z"
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                circleNumber = "Number of Circles: $z"
            }
        }
        tv!!.text = """
            $circleNumber
            """.trimIndent()
        return true
    }
}*/