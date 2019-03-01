package com.averoes.daff.listmahasiswa.services;

/**
 * Created by daff on 21/02/19 at 20:57.
 */

public interface LoadCallback {

    void onPreLoad();
    void onProgressUpdate(long progress);
    void onLoadSucces();
    void onLoadFailed();
    void onLoadCancel();
}
