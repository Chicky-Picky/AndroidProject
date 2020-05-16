package com.example.androidapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.androidapplication.views.MyCanvasView



class MainActivity : AppCompatActivity() {

    public var mode: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView = MyCanvasView(this)
        setContentView(R.layout.activity_main)

    }


}