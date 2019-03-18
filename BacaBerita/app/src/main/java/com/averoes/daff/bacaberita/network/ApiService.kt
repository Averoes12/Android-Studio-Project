package com.averoes.daff.bacaberita.network

import com.averoes.daff.bacaberita.model.ResponseBerita

import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by daff on 05/02/19.
 */
//disini adalah tempat untuk menyimpan methode request yang akan kita kirimkan
interface ApiService {
    //@METHODE_REQUEST("end_point")
    @GET("tampil_berita.php")
    fun showAllBerita(): Call<ResponseBerita>

}
