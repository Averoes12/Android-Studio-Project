package com.averoes.myasynctaskloader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import android.util.Log;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<WeatherItems>> {

    private ArrayList<WeatherItems> data;
    private boolean hasResult = false;
    private String kumpulanKota;

    public MyAsyncTaskLoader(Context context, String kumpulanKota) {
        super(context);

        onContentChanged();
        this.kumpulanKota = kumpulanKota;
    }

    @Override
    protected void onStartLoading() {
        if (takeContentChanged())
            forceLoad();
        else if (hasResult)
            deliverResult(data);
    }

    @Override
    public void deliverResult(ArrayList<WeatherItems> data) {
        this.data = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();

        onStopLoading();
        if (hasResult){
            onReleaseResources(data);
            data = null;
            hasResult = false;
        }
    }

    private static final String API_KEY = "851b6b3049bde3e42a7354349abfe9ef";

    @Override
    public ArrayList<WeatherItems> loadInBackground() {
        SyncHttpClient client = new SyncHttpClient();

        final ArrayList<WeatherItems> weatherItemses = new ArrayList<>();
        String url = "http://api.openweathermap.org/data/2.5/group?id=" + kumpulanKota+ "&units=metric&appid=" + API_KEY;

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                setUseSynchronousMode(true);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");

                    for (int i = 0; i<list.length(); i++){
                        JSONObject weather = list.getJSONObject(i);
                        WeatherItems weatherItems = new WeatherItems(weather);

                        weatherItemses.add(weatherItems);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                Log.d("Status Response", "Succes Response");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                Log.e("Status Response", "Failure Response");
            }
        });

        return weatherItemses;
    }

    protected void onReleaseResources(ArrayList<WeatherItems> data) {


    }
}
