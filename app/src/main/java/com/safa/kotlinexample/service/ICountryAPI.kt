package com.safa.kotlinexample.service

import com.safa.kotlinexample.model.Country
import io.reactivex.Single
import retrofit2.http.GET

interface ICountryAPI {

    //https://raw.githubusercontent.com/serpilsafa/MyAPIResource/master/counties.json

    @GET("serpilsafa/MyAPIResource/master/counties.json")
    fun getDataFromApi():Single<List<Country>>

}