package com.averoes.daff.barvolume;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText Panjang, Lebar, Volume;
    Button Hitung;
    TextView Result;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Panjang = (EditText)findViewById(R.id.Panjang);
        Lebar = (EditText)findViewById(R.id.Lebar);
        Volume = (EditText)findViewById(R.id.Volume);
        Hitung = (Button)findViewById(R.id.Hitung);
        Result = (TextView)findViewById(R.id.Result);

        Hitung.setOnClickListener(this);


        if (savedInstanceState != null){

            String result = savedInstanceState.getString(STATE_RESULT);
            Result.setText(result);
        }
    }

    @Override

    public void onClick(View v){

        if (v.getId() == R.id.Hitung) {

            String inputPanjang = Panjang.getText().toString().trim();
            String inputLebar = Lebar.getText().toString().trim();
            String inputVolume = Volume.getText().toString().trim();

            

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;


            if (TextUtils.isEmpty(inputPanjang)) {
                isEmptyFields = true;
                Panjang.setError("Tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLebar)) {
                isEmptyFields = true;
                Lebar.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputVolume)) {
                isEmptyFields = true;
                Volume.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputPanjang);
            Double width = toDouble(inputLebar);
            Double height = toDouble(inputVolume);

            if (length == null) {
                isInvalidDouble = true;
                Panjang.setError("Field ini harus berupa nomer yang valid");
            }

            if (width == null) {
                isInvalidDouble = true;
                Lebar.setError("Field ini harus berupa nomer yang valid");
            }

            if (height == null) {
                isInvalidDouble = true;
                Volume.setError("Field ini harus berupa nomer yang valid");
            }

            if (!isEmptyFields && !isInvalidDouble) {
                double volume = length * width * height;
                Result.setText(String.valueOf(volume));
            }
        }
    }


     Double toDouble(String str) {


        try {
            return Double.valueOf(str);
        }catch (NumberFormatException e){
            return null;
        }
    }

    private static final String STATE_RESULT="state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, Result.getText().toString());
    }

}



