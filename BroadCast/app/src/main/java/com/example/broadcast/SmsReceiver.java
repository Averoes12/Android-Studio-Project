package com.example.broadcast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SmsReceiver extends AppCompatActivity implements View.OnClickListener {

    TextView smsFrom;
    TextView whatMessage;
    Button close;

    public final static String EXTRA_SMS_NO = "nomer";
    public static final String EXTRA_SMS_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_receiver);

        setTitle("Incoming Message");

        close = findViewById(R.id.btn_close);
        smsFrom = findViewById(R.id.nomer);
        whatMessage = findViewById(R.id.message);

        close.setOnClickListener(this);

        String pesan = getIntent().getStringExtra(EXTRA_SMS_MESSAGE);
        String nomer = getIntent().getStringExtra(EXTRA_SMS_NO);

        smsFrom.setText(String.format("from : %s",nomer));
        whatMessage.setText(pesan);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_close){
            finish();
        }
    }
}
