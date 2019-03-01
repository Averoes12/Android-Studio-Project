package com.averoes.daff.listmahasiswa;

/**
 * Created by daff on 21/02/19 at 22:15.
 */

interface HandlerCallback {

    void onPreparation();
    void onUpdateProgress(long progress);
    void onLoadSuccess();
    void onLoadFailed();
    void loadCancel();
}
