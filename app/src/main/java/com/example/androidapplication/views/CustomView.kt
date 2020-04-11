package com.example.androidapplication.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View


class CustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    // Paint object for coloring and styling
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // Some colors for the face background, eyes and mouth.
    private var drawingFieldColor = Color.WHITE
    private var borderColor = Color.BLACK

    // Face border width in pixels
    private var borderWidth = 4.0f

    // View size in pixels
    private var size = 320

    override fun onDraw(canvas: Canvas) {
        // call the super method to keep any drawing from the parent side.
        super.onDraw(canvas)

        createDrawingField(canvas)
    }




private fun createDrawingField(canvas: Canvas) {

    // создаем пустой прямоугольник и задаем координаты верхней левой и нижней правой точек





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

