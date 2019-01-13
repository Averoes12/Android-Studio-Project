package com.averoes.daff.intentapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_PERSON = "extra_person";
    TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        data =  findViewById(R.id.text);

        Person person = getIntent().getParcelableExtra(EXTRA_PERSON);
        String text = "Name : " + person.getName() + ",\nEmail : " + person.getEmail() + ",\nAge : " + person.getAge() + ",\nLocation : " + person.getCity();

        data.setText(text);
    }
}

