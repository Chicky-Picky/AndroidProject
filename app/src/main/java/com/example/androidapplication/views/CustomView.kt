package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var drawingFieldColor = Color.WHITE
    private var borderColor = Color.BLACK
    private var borderWidth = 4.0f
    private var size = 320

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        createDrawingField(canvas)
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


    }



}

