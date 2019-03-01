package com.averoes.daff.listmahasiswa;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.averoes.daff.listmahasiswa.services.DataMangerService;
import java.lang.ref.WeakReference;
import static com.averoes.daff.listmahasiswa.services.DataMangerService.FAILED_MESSAGE;
import static com.averoes.daff.listmahasiswa.services.DataMangerService.PREPARATION_MESSAGE;
import static com.averoes.daff.listmahasiswa.services.DataMangerService.SUCCESS_MESSAGE;
import static com.averoes.daff.listmahasiswa.services.DataMangerService.UPDATE_MESSAGE;

public class MainActivity extends AppCompatActivity implements HandlerCallback {
    ProgressBar progress;
    Messenger messenger;

    Messenger boundService;
    boolean serviceBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progress);

        Intent serviceIntent = new Intent(MainActivity.this, DataMangerService.class);
        messenger = new Messenger(new IncomingHandler(this));
        serviceIntent.putExtra(DataMangerService.ACTIVITY_HANDLER, messenger);

        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            boundService = new Messenger(service);
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };


    @Override
    public void onPreparation() {
        Toast.makeText(this, "Starting to load data", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onUpdateProgress(long progress) {

        Log.e("PROGRESS", "updateProgress: " + progress);
        this.progress.setProgress((int) progress);
    }

    @Override
    public void onLoadSuccess() {

        Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, MahasiswaActivity.class));
        finish();
    }

    @Override
    public void onLoadFailed() {

        Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void loadCancel() {

        finish();
    }

    public static class IncomingHandler extends android.os.Handler{
        WeakReference<HandlerCallback> weakCallback;

        IncomingHandler(HandlerCallback callback){
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what){
                case PREPARATION_MESSAGE:
                    weakCallback.get().onPreparation();
                    break;
                case UPDATE_MESSAGE:
                    Bundle bundle = message.getData();
                    long progress = bundle.getLong("KEY_PROGRESS");
                    weakCallback.get().onUpdateProgress(progress);
                    break;
                case SUCCESS_MESSAGE:
                    weakCallback.get().onLoadSuccess();
                    break;
                case FAILED_MESSAGE:
                    weakCallback.get().onLoadFailed();
                    break;
                default:
                    weakCallback.get().loadCancel();
            }
        }
    }


}
