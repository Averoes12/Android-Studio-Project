package com.averoes.daff.intentapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    RadioGroup foodMenu;
    Button chooseMenu;

    public static String EXTRA_SELECTED_VALUE = "value";
    public static int RESULT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        chooseMenu =  findViewById(R.id.btn_choose);
        foodMenu =  findViewById(R.id.foodMenu);

        chooseMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_choose){

            if (foodMenu.getCheckedRadioButtonId() != 0){

                int value = 0;

                switch (foodMenu.getCheckedRadioButtonId()){

                    case R.id.ff:
                        value = 20;
                        break;

                    case R.id.ck:
                        value = 50;
                        break;

                    case R.id.tb:
                        value = 70;
                        break;
                }

                Intent result = new Intent();
                result.putExtra(EXTRA_SELECTED_VALUE, value);
                setResult(RESULT_CODE, result);
                finish();
            }
        }
    }
}
