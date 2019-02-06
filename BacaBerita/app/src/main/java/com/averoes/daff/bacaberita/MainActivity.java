package com.averoes.daff.bacaberita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.averoes.daff.bacaberita.adapter.BeritaAdapter;
import com.averoes.daff.bacaberita.model.BeritaItem;
import com.averoes.daff.bacaberita.model.ResponseBerita;
import com.averoes.daff.bacaberita.network.ConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    //deklarasi widget
    public RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendRequestBerita();
        //Inisialisasi widget
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);

        showRecyclerList();
    }

    public void showRecyclerList(){
        //layout manager untuk recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void sendRequestBerita(){
        ConfigRetrofit.getInstance().showAllBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {

                ArrayList<BeritaItem> berita_item = (ArrayList<BeritaItem>) response.body().getBerita();
                //check response sukses
                if (response.isSuccessful()){
                    Log.d("Response", "Succes");
                    //tampung data response body ke dalam sebuah variabel
                    ArrayList<BeritaItem> list_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
//==> Jika status == true
                    if (status){
                        //buat adapter recycler view
                        BeritaAdapter adapter = new BeritaAdapter(MainActivity.this, list_berita);
                        recyclerView.setAdapter(adapter);
                }else {
                        //jika bukan true
                        Toast.makeText(getApplicationContext(), "Tidak ada berita untuk hari ini", Toast.LENGTH_SHORT).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {

                t.printStackTrace();
            }
        });

    }
}
