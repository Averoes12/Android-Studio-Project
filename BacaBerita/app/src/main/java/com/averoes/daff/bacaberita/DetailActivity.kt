package com.averoes.daff.bacaberita

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import butterknife.BindView
import butterknife.ButterKnife
import com.averoes.daff.bacaberita.R.id.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ButterKnife.bind(this)
        showDetailBerita()


    }

    @SuppressLint("SetJavaScriptEnabled", "SetTextI18n")
    private fun showDetailBerita() {

        val judul_berita = intent.getStringExtra(TITTLE)
        val tangal_berita = intent.getStringExtra(DATE)
        val penulis_berita = intent.getStringExtra(WRITER)
        val isi_berita = intent.getStringExtra(CONTENT)
        val foto_berita = intent.getStringExtra(IMG)

        val terbit = findViewById<TextView>(R.id.date_detail)
        val penulis = findViewById<TextView>(R.id.penulis_detail)
        val content_web = findViewById<WebView>(R.id.web_content)
        val imgDetail = findViewById<ImageView>(R.id.img_detail)

        penulis.setText("Oleh : $penulis_berita")
        terbit.setText(tangal_berita)

        Glide.with(this).load(foto_berita).apply(RequestOptions()).into(imgDetail)
        content_web.settings.javaScriptEnabled = true
        content_web.loadData(isi_berita, "text/html; charset=utf-8", "UTF-8")


    }

    companion object {
        val TITTLE = "Judul"
        val WRITER = "Penulis"
        val DATE = "Tanggal"
        val CONTENT = "Isi"
        val IMG = "Photo"
    }
}
