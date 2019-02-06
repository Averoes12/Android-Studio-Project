package com.averoes.daff.asynctask;

public interface MyAsyncCallback {
    void onPreExecute();
    void onPostExecute(String text);
}
