package com.averoes.daff.mvvmapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.averoes.daff.mvvmapp.R;
import com.averoes.daff.mvvmapp.viewmodel.UserVIewModel;

public class MainActivity extends AppCompatActivity {

    EditText in_email,in_password;
    Button btn_login;
    UserVIewModel userVIewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        in_email = findViewById(R.id.in_email);
        in_password = findViewById(R.id.in_password);
        btn_login = findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userVIewModel.updateModel(in_email.getText().toString(),
                        in_password.getText().toString());
                userVIewModel.validLogin();
            }
        });
    }
}
