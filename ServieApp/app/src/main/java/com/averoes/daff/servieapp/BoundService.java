package com.averoes.daff.servieapp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class BoundService extends Service {

    final long startTime = System.currentTimeMillis();
    private final IBinder myBinder = new MyLocalBinder();
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "On Bind", Toast.LENGTH_SHORT).show();
        Log.d("Bind","On Bind");
        timer.start();
        return myBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(getApplicationContext(), "On UnBind", Toast.LENGTH_SHORT).show();
        Log.d("UnBind","On UnBind");
        timer.cancel();
        return super.onUnbind(intent);
    }

    public String getCurrentTime() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy", Locale.getDefault());
        return dateFormat.format(new Date());
    }

    CountDownTimer timer = new CountDownTimer(10000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            long timer = System.currentTimeMillis() - startTime;
            Toast.makeText(getApplicationContext(), "=> " + timer, Toast.LENGTH_SHORT).show();
            Log.d(TAG,"OnTick: " + timer);
        }

        @Override
        public void onFinish() {

        }
    };

    public class MyLocalBinder extends Binder {
        BoundService getService(){
            return BoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "OnCreate", Toast.LENGTH_SHORT).show();
        Log.d("OnCreate", "On Create");
    }

    @Override
    public void onDestroy() {
        Toast.makeText(getApplicationContext(), "On Destroy", Toast.LENGTH_SHORT).show();
        Log.d("Destroy","On Destroy");
        super.onDestroy();
    }
}
