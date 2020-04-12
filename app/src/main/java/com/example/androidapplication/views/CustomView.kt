package com.example.androidapplication.views

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.TextView
import com.example.androidapplication.R


class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //createDrawingField(canvas)
    }



    private fun createDrawingField(canvas: Canvas) {

        val width = width.toFloat()
        val height = height.toFloat()

        val paint = Paint()
        paint.isAntiAlias = true
        paint.color = Color.BLACK
        paint.strokeWidth = 5f
        paint.style = Paint.Style.FILL


        val rect = RectF()
        rect[69F, 69F, width - 69F] = height - 69F
        canvas.drawRoundRect(rect, 100F, 100F, paint)
        paint.color = Color.WHITE
        rect[75F, 75F, width - 75F] = height - 75F
        canvas.drawRoundRect(rect, 100F, 100F, paint)




        /*var tv: TextView? = null
        var x = 0f
        var y = 0f
        var z = 0
        var circleNumber: String? = null






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
        }*/

    }





}

