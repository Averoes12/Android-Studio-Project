package com.averoes.daff.learnespresso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    public final static String EXTRA = "Extra";
    @BindView(R.id.text_result) TextView intent_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Second Activity");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        String input_result = getIntent().getStringExtra(EXTRA);
        intent_result.setText(input_result);

        ButterKnife.bind(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
