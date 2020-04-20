package com.safa.kotlinexample.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.safa.kotlinexample.model.Country
import com.safa.kotlinexample.service.CountryDatabase
import com.safa.kotlinexample.service.CountryService
import com.safa.kotlinexample.util.CustomSharedPreferences
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ShowCountryListViewModel(application: Application): BaseViewModel(application) {

    val countries = MutableLiveData<List<Country>>()

    val disposable = CompositeDisposable()
    val service = CountryService()

    val customSharedPreferences = CustomSharedPreferences()
    val expectedTimeAravage = 10*60.1000*1000*1000L

    fun refleshData(){
        val time = customSharedPreferences.returnTime()
        if(time != null && time != 0L && System.nanoTime()- time < expectedTimeAravage ){
            refreshDataFromDB()
        }else{
            refreshDataFromApi()
        }
    }

    fun refreshDataFromApi(){
        disposable.add(
            service.getData()
            .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        println("api " + t)
                        insertDataIntoDatabase(t)
                    }

                    override fun onError(e: Throwable) {
                        println("api "+ e.printStackTrace())
                    }

                }))

        customSharedPreferences.enterTime(System.nanoTime())
    }

    fun refreshDataFromDB(){
        launch {
            val countries = CountryDatabase(getApplication()).countryDao().getAllCountries()
            setCountriesValue(countries)
        }

    }
    fun insertDataIntoDatabase(countriesList: List<Country>){
        launch {
            val dao = CountryDatabase(getApplication()).countryDao()
            dao.deleteAllCountries()
            val uuidList = dao.insertAllCountries(*countriesList.toTypedArray())
            var i = 0
            while (i<countriesList.size){
                countriesList[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            setCountriesValue(countriesList)
        }
    }

    fun setCountriesValue(countriesList: List<Country>){
        countries.value = countriesList
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}