package com.averoes.myasynctaskloader;

import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<ArrayList<WeatherItems>> {

    ListView listView;
    WeatherAdapter adapter;
    EditText editKota;
    Button cari;

    static final String CITY = "City";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new WeatherAdapter(this);
        adapter.notifyDataSetChanged();
        listView = findViewById(R.id.list_item);
        listView.setAdapter(adapter);

        editKota = findViewById(R.id.kota);
        cari = findViewById(R.id.btn_cari);

        cari.setOnClickListener(this);

        String kota = editKota.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString(CITY, kota);
        getLoaderManager().initLoader(0, bundle,  this);
    }

    public Loader<ArrayList<WeatherItems>> onCreateLoader(int id, Bundle args){
        String kumpulanKota = "";
        if (args != null){
            kumpulanKota = args.getString(CITY);
        }
        return new MyAsyncTaskLoader(this, kumpulanKota);
    }


    public void onLoadFinished(Loader<ArrayList<WeatherItems>> loader, ArrayList<WeatherItems> data){
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<WeatherItems>> loader) {

    }


    @Override
    public void onClick(View v) {

        String kota = editKota.getText().toString();

        if(TextUtils.isEmpty(kota))return;

        Bundle bundle = new Bundle();
        bundle.putString(CITY, kota);

        getLoaderManager().restartLoader(0, bundle, MainActivity.this);
    }
}
