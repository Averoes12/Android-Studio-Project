package com.averoes.daff.servieapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    BoundService boundService;
    boolean isBind = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) service;
            boundService = binder.getService();
            isBind = true;
        }//menyatakan bahwa service diikat

        @Override
        public void onServiceDisconnected(ComponentName name) {

            isBind = false;
        }
    };//Menyatakan bahwa service sudah tidak diikat

    public void showTime(View view){
        String currentTime = boundService.getCurrentTime();
        TextView showtime = findViewById(R.id.time);
        showtime.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = new Intent(getApplicationContext(), BoundService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

    }
}
