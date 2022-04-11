package com.pourkazemi.mahdi.maktab_hw_15_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private val _listOfCity = MutableLiveData<MutableList<City>>()//.apply { value?.addAll(createList())  }
    val listOfCity: LiveData<List<City>>
        get() = Transformations.map(_listOfCity) {
            it.toList()
        }

    init {
        _listOfCity.postValue(createList().toMutableList())
    }

    fun updateListCity(position: Int) {
        // if (city.id == position) {
        val uCity = _listOfCity.value?.get(position)
        uCity?.let {
            it.isSelected = !it.isSelected
            _listOfCity.value?.set(position, it)
        }
    }

    fun removeOfList(position: Int) {
        _listOfCity.value?.removeAt(position)
        //_listOfCity.postValue(_listOfCity.value)
    }

    private fun createList(): List<City> {
        return listOf(
            City(0, "Tokyo-Yokohama", false),
            City(1, "Jakarta", false),
            City(2, "Delhi", false),
            City(3, "Manila", false),
            City(4, "Seoul", false),
            City(5, "Shanghai", false),
            City(6, "Karachi", false),
            City(7, "Beijing", false),
            City(8, "New York", false),
            City(9, "Sao Paulo", false),
            City(10, "Mexico", false),
            City(11, "Mumbai", false),
            City(12, "Moscow", false),
            City(13, "Bangkok", false),
            City(14, "Tehran", false)
        )
    }
}
