package com.safa.kotlinexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.safa.kotlinexample.model.Country
import com.safa.kotlinexample.service.CountryService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ShowCountryListViewModel: ViewModel() {

    val countries = MutableLiveData<List<Country>>()

    val disposable = CompositeDisposable()
    val service = CountryService()

    fun refleshData(){


    }

    fun refreshDataFromApi(){
        disposable.add(
            service.getData()
            .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        println("api " + t)
                        countries.value = t
                    }

                    override fun onError(e: Throwable) {
                        println("api "+ e.printStackTrace())
                    }

                }))
    }
}