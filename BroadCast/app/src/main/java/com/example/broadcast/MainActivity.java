package com.example.broadcast;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCheck = findViewById(R.id.btn_check);
        btnCheck.setOnClickListener(this);
    }

    final int SMS_REQUEST_CODE = 101;

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_check){
            PermissionManager.check(this, Manifest.permission.RECEIVE_SMS, SMS_REQUEST_CODE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int [] grantResult){

        if (requestCode == SMS_REQUEST_CODE){

            if (grantResult[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permintaan diterima", Toast.LENGTH_SHORT).show();
                }else {
                Toast.makeText(this, "Permintaan Ditolak",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
