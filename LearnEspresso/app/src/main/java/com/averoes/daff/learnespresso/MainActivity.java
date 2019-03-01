package com.averoes.daff.learnespresso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn_move_activity) Button btn_move;
    @BindView(R.id.btn_ubah_text) Button btn_edit;
    @BindView(R.id.reuslt_text) TextView result_view;
    @BindView(R.id.input_text) EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() == null){
            getSupportActionBar().setTitle("Learn Espresso");
        }
        btn_edit.setOnClickListener(myListener);
        btn_move.setOnClickListener(myListener);

        ButterKnife.bind(this);
    }

    View.OnClickListener myListener  = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.btn_ubah_text:
                    input.setText(getResources().getString(R.string.result));
                    String input_text = input.getText().toString().trim();
                    result_view.setText(input_text);
                    break;
                case R.id.btn_move_activity:
                    String text = input.getText().toString().trim();
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra(SecondActivity.EXTRA, text);
                    startActivity(intent);
                    break;

            }
        }
    };
}
