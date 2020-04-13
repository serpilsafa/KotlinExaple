package com.safa.kotlinexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safa.kotlinexample.model.Country

class ShowCountryListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()

    fun refleshData(){
        val country1 = Country("Turkey","Ankara","Asia","Turkish","TRY","")
        val country2 = Country("Turkey","Ankara","Asia","Turkish","TRY","")
        val country3 = Country("Turkey","Ankara","Asia","Turkish","TRY","")

        val countryList = listOf(country1, country2, country3)
        countries.value = countryList
    }
}