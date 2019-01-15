package com.averoes.daff.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

// FragmentManager yang merupakan antarmuka untuk mengorganisir obyek fragment yang terdapat didalam sebuah Activity.
//FragmentManager yang berasal dari Android Support Library v4, agar bisa kompatibel ke versi Android sebelumnya (Backward Compability).

        FragmentManager fragmentManager = getSupportFragmentManager();

        //FragmentTransaction merupakan api untuk melakukan operasi pada fragment seperti add(), replace(), commit() dsb.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();


        Fragment fragment = fragmentManager.findFragmentByTag(HomeFragment.class.getSimpleName());
        if (!(fragment instanceof  HomeFragment)){
//Di sinilah proses manipulasi penambahan fragment kedalam activity terjadi.
// Metode add() akan menambahkan obyek fragment kedalam layout container.
// Layout container ini merupakan obyek FrameLayout dengan ID frame_container. Ia memiliki tag dengan nama kelas dari HomeFragment itu sendiri.
            fragmentTransaction.add(R.id.frame_container, homeFragment, HomeFragment.class.getSimpleName());
            Log.d("Flexible Fragment", "Fragment Name: " + HomeFragment.class.getSimpleName());

//Baris dibawah akan meminta obyek fragmentTransaction untuk melakukan pemasangan obyek secara asynchronous untuk ditampilkan ke antar muka pengguna (user interface).
            fragmentTransaction.commit();
        }
    }
}
