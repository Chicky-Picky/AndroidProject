package com.example.androidapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.example.androidapplication.views.MyCanvasView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView = MyCanvasView(this)
        //setContentView(myCanvasView)
        setContentView(R.layout.activity_main)

        var model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        //var listOfListOfPoints = model.getListOfListOfPoints()
    }



}