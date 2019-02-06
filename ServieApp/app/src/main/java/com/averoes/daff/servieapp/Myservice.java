package com.averoes.daff.servieapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class Myservice extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Download Stopped", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
            double result = DownloadFile(new URL("https://download.virtualbox.org/virtualbox/6.0.0/virtualbox-6.0_6.0.0-127566~Ubuntu~bionic_amd64.deb"));
            Toast.makeText(getApplicationContext(), "Download Size " + result + " MB",Toast.LENGTH_SHORT).show();
        }catch (MalformedURLException e){
            e.printStackTrace();

        }
        return START_STICKY;
    }

    private double DownloadFile(URL url) {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return 12.9;
    }
}
