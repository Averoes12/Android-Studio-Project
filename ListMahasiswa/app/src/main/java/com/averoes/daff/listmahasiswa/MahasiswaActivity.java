package com.averoes.daff.listmahasiswa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.averoes.daff.listmahasiswa.adapter.MahasiswaAdapter;
import com.averoes.daff.listmahasiswa.database.MahasiswaHelper;
import com.averoes.daff.listmahasiswa.model.MahasiswaModel;

import java.util.ArrayList;

public class MahasiswaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MahasiswaAdapter adapter;
    MahasiswaHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        adapter = new MahasiswaAdapter();
        helper = new MahasiswaHelper(this);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        helper.open();

        ArrayList<MahasiswaModel> mahasiswaModels = helper.getAllData();

        helper.close();

        adapter.setList_mahasiswa(mahasiswaModels);

    }
}
