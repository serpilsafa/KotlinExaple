package com.safa.kotlinexample.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class CustomSharedPreferences {


    companion object{

        @Volatile var instance: CustomSharedPreferences? = null
        var sharedPreferences:SharedPreferences? = null
        val TIME = "read_time"
        val lock = Any()

        fun invoke(context: Context) = instance ?: synchronized(lock){
            instance ?: createSharedPreferances(context).also {
                instance = it
            }
        }

        fun createSharedPreferances(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

            return CustomSharedPreferences()
        }
    }

    fun enterTime(time: Long){
        sharedPreferences?.edit(commit = true){
            putLong(TIME,time)
        }
    }

    fun returnTime()= sharedPreferences?.getLong(TIME, 0)

}