package com.averoes.daff.servieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSerevice(View view){
//Menjalankan service
        startService(new Intent(getApplicationContext(), MyIntentService.class));
    }
//Service berhenti
    public void stopService(View view){
        stopService(new Intent(getApplicationContext(), MyIntentService.class));
    }

    public void move(View v){
        Intent i = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(i);
    }
}
