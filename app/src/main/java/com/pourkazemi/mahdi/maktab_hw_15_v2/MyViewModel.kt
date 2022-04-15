package com.pourkazemi.mahdi.maktab_hw_15_v2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.pourkazemi.mahdi.maktab_hw_15_v2.model.City

class MyViewModel : ViewModel() {
    private val _listOfCity = MutableLiveData<MutableList<City>>()
    val listOfCity: LiveData<List<City>>
        get() = Transformations.map(_listOfCity) {
            it.toList()
        }
    private val _listOfSelectedCity = MutableLiveData<MutableSet<City>>()
    val listOfSelectedCity: LiveData<Set<City>>
        get() = Transformations.map(_listOfSelectedCity) {
            it.toSet()
        }

    init {
        _listOfCity.postValue(createList().toMutableList())
        _listOfSelectedCity.postValue(listOf<City>().toMutableSet())
    }

    fun addToSelected(position: Int) {
        val uCity = _listOfCity.value?.get(position)
        uCity?.let {
            _listOfSelectedCity.value?.add(it)
        }
    }
    fun changeSelection(position: Int){
        val rCity = _listOfCity.value?.get(position)
        rCity?.let {
            it.isSelected = !it.isSelected
            _listOfCity.value?.set(position, it)
        }
    }

    fun removeOfSelectedList(position: Int) {
        _listOfSelectedCity.value?.remove(_listOfSelectedCity.value?.elementAt(position))
        //changeSelection(position)
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
