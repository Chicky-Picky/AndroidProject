package com.example.androidapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.algo.Point

public class MyViewModel : ViewModel() {
    var listOfListOfPoints : MutableLiveData<ArrayList<ArrayList<Point>>>? = null

    /*
    fun getListOfListOfPoints(): LiveData<ArrayList<ArrayList<Point>>> {
        if (listOfListOfPoints == null) {
            listOfListOfPoints = MutableLiveData()
            loadListOfListOfPoints()
        }
    }

    private fun loadListOfListOfPoints() {
    }
    */
}