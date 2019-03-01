package com.averoes.daff.listmahasiswa.services;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.averoes.daff.listmahasiswa.database.MahasiswaHelper;
import com.averoes.daff.listmahasiswa.preference.AppReference;

public class DataMangerService extends Service {

    public static final int PREPARATION_MESSAGE = 0;
    public static final int UPDATE_MESSAGE = 1;
    public static final int SUCCESS_MESSAGE = 2;
    public static final int FAILED_MESSAGE = 3;
    public static final int CANCEL_MESSAGE = 4;
    public static final String ACTIVITY_HANDLER = "activity_handler";
    private String TAG = DataMangerService.class.getSimpleName();
    private LoadData loadData;
    private Messenger messenger;

    //untuk menerima dan mengirim return data ke activity pemanggil

    LoadCallback callback = new LoadCallback() {
        @Override
        public void onPreLoad() {

            Message message = Message.obtain(null, PREPARATION_MESSAGE);
            try {
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onProgressUpdate(long progress) {
            try {
                Message message = Message.obtain(null, UPDATE_MESSAGE);
                Bundle bundle = new Bundle();
                bundle.putLong("KEY_PROGRESS", progress);
                message.setData(bundle);
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onLoadSucces() {
            Message message =  Message.obtain(null, SUCCESS_MESSAGE);
            try {
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }

        }

        @Override
        public void onLoadFailed() {
            Message message = Message.obtain(null, FAILED_MESSAGE);
            try {
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onLoadCancel() {
            Message message = Message.obtain(null, CANCEL_MESSAGE);

            try {
                messenger.send(message);
            }catch (RemoteException e){
                e.printStackTrace();
            }
        }
    };

    public DataMangerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MahasiswaHelper helper = MahasiswaHelper.getInstance(getApplicationContext());
        AppReference appReference = new AppReference(getApplicationContext());

        loadData = new LoadData(helper, appReference, callback, getResources());

        Log.d(TAG, "On Create");
    }

    @Override
    public IBinder onBind(Intent intent) {

        messenger = intent.getParcelableExtra(ACTIVITY_HANDLER);

        loadData.execute();
        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "on Unbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "on Rebind");
    }
}
