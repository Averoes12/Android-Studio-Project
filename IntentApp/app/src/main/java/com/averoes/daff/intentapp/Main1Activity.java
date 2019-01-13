package com.averoes.daff.intentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main1Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_NAME = "name";
    public static final String EXTRA_AGE = "";

    TextView data;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        data = findViewById(R.id.data);

        back = findViewById(R.id.back);
        back.setOnClickListener(this);

        //Setelah data dikirimkan, selanjutnya adalah mengambil data tersebut,dengan metode get.

        String name = getIntent().getStringExtra(EXTRA_NAME);
        int age = getIntent().getIntExtra(EXTRA_AGE, 0);

        String text = "Name: " + name + "\nAge: " + age;
        data.setText(text);


    }

    public void onClick(View v) {

        switch (v.getId()){

            case R.id.back:
                Intent back = new Intent(Main1Activity.this, MainActivity.class);
                startActivity(back);
                break;
        }
    }
}
