package com.averoes.daff.bacaberita.network;

import android.support.annotation.NonNull;

import com.averoes.daff.bacaberita.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by daff on 05/02/19.
 */

public class ConfigRetrofit {
//setInit digunakan untuk menginisialisasi Class retrofit, di dalam class ini juga kita akan memberitahu retrofit arah api kita
    @NonNull
    public static Retrofit setInit(){
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build();
    }
//get instance digunakan untuk menghubungkan class retrofit dengan class api service
    public static ApiService getInstance() {
        return setInit().create(ApiService.class);
    }
}
