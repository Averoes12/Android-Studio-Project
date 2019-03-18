package com.averoes.daff.bacaberita

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.averoes.daff.bacaberita.R.id.recycler_view

import com.averoes.daff.bacaberita.adapter.BeritaAdapter
import com.averoes.daff.bacaberita.model.BeritaItem
import com.averoes.daff.bacaberita.model.ResponseBerita
import com.averoes.daff.bacaberita.network.ConfigRetrofit


import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    //deklarasi widget
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequestBerita()
        //Inisialisasi widget
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView?.setHasFixedSize(true)

        showRecyclerList()


    }

    fun showRecyclerList() {
        //layout manager untuk recycler view
        recyclerView?.layoutManager = LinearLayoutManager(this)
    }

    fun sendRequestBerita() {
        ConfigRetrofit.instance.showAllBerita().enqueue(object : Callback<ResponseBerita> {
            override fun onResponse(call: Call<ResponseBerita>, response: Response<ResponseBerita>) {

                val berita_item = response.body()!!.berita
                //check response sukses
                if (response.isSuccessful) {
                    Log.d("Response", "Succes")
                    //tampung data response body ke dalam sebuah variabel
                    val list_berita = response.body()!!.berita
                    val status = response.body()!!.isStatus
                    //==> Jika status == true
                    if (status) {
                        //buat adapter recycler view
                        val adapter = BeritaAdapter(this@MainActivity, list_berita)
                        recyclerView?.adapter = adapter
                    } else {
                        //jika bukan true
                        Toast.makeText(applicationContext, "Tidak ada berita untuk hari ini", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun onFailure(call: Call<ResponseBerita>, t: Throwable) {

                t.printStackTrace()
            }
        })

    }

    companion object {
        internal val STATE = "State"
    }
}
