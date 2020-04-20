package com.safa.kotlinexample.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safa.kotlinexample.model.Country
import com.safa.kotlinexample.service.CountryDatabase
import com.safa.kotlinexample.view.CountryDetailFragmentDirections
import kotlinx.coroutines.launch

class CountryDetailViewModel(application: Application) : BaseViewModel(application) {

    val county = MutableLiveData<Country>()

    fun getCountry(uuid: Int){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            county.value = dao.getCountry(uuid)
        }
    }

}