package com.safa.kotlinexample.service

import com.safa.kotlinexample.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CountryService {

    val BASE_URL:String = "https://raw.githubusercontent.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(ICountryAPI::class.java)

    fun getData(): Single<List<Country>>{
        return api.getDataFromApi()
    }
}