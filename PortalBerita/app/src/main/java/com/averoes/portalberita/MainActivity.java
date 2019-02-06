package com.averoes.portalberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.averoes.portalberita.model.BeritaItem;
import com.averoes.portalberita.model.ResponseBerita;
import com.averoes.portalberita.network.ConfigRetrofit;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendRequestBerita();
    }

    private void sendRequestBerita() {
        ConfigRetrofit.getInstance().showAllBerita().enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                boolean status = response.body().isStatus();
                ArrayList<BeritaItem> beritaItem = (ArrayList<BeritaItem>) response.body().getBerita();

                if (status){
                    Log.d("Response" , "Succes");
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {

                Log.e("Respose", "Failure");
            }
        });
    }
}
