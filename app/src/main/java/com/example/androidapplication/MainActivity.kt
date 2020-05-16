package com.example.androidapplication

import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import com.example.androidapplication.views.MyCanvasView


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val myCanvasView = MyCanvasView(this)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        setContentView(myCanvasView)
        //setContentView(R.layout.activity_main)

        var model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        //var listOfListOfPoints = model.getListOfListOfPoints()
    }



}