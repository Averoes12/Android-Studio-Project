package com.averoes.daff.bacaberita.network

import com.averoes.daff.bacaberita.BuildConfig

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by daff on 05/02/19.
 */

object ConfigRetrofit {
    //get instance digunakan untuk menghubungkan class retrofit dengan class api service
    val instance: ApiService
        get() = setInit().create(ApiService::class.java)

    //setInit digunakan untuk menginisialisasi Class retrofit, di dalam class ini juga kita akan memberitahu retrofit arah api kita
    fun setInit(): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }
}
