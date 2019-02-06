package com.averoes.daff.servieapp;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS

    // TODO: Rename parameters


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            double result = DownloadFile(new URL("https://download.virtualbox.org/virtualbox/6.0.0/virtualbox-6.0_6.0.0-127566~Ubuntu~bionic_amd64.deb"));
            Log.d("Intent Service", "Download Size " + result + "MB");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private double DownloadFile(URL url) {
        try {
            Thread.sleep(6900);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return 48.3;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Download has Finished", Toast.LENGTH_SHORT).show();
        Log.d("Intent Service","Download finished");
    }
}
