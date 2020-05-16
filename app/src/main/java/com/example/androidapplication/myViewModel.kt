package com.example.androidapplication

import androidx.lifecycle.ViewModel
import com.example.algo.Point
import com.example.algo.Points
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MyViewModel : ViewModel() {
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
}