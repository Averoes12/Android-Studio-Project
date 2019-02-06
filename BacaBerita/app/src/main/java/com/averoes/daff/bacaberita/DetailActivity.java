package com.averoes.daff.bacaberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    public static final String TITTLE  = "Judul";
    public static final String WRITER  = "Penulis";
    public static final String DATE    = "Tanggal";
    public static final String CONTENT = "Isi";
    public static final String IMG     = "Photo";

    @BindView(R.id.date_detail) TextView  date;
    @BindView(R.id.penulis_detail) TextView penulis;
    @BindView(R.id.judul_detail) TextView judul;
    @BindView(R.id.web_content) WebView content;
    @BindView(R.id.img_detail) ImageView image_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        image_detail = findViewById(R.id.img_detail);

        ButterKnife.bind(this);
        showDetailBerita();
    }

    private void showDetailBerita(){

        String judul_detail = getIntent().getStringExtra(TITTLE);
        String date_detail = getIntent().getStringExtra(DATE);
        String writer_detail = getIntent().getStringExtra(WRITER);
        String content_detail = getIntent().getStringExtra(CONTENT);
        String img_detail = getIntent().getStringExtra(IMG);

        judul.setText(judul_detail);
        penulis.setText("Oleh " + writer_detail);
        date.setText(date_detail);

        content.getSettings().setJavaScriptEnabled(true);
        content.loadData(content_detail, "text/html; charset=utf-8", "UTF-8");

        Glide.with(this).load(img_detail).apply(new RequestOptions()).into(image_detail);

    }
}
