package com.averoes.daff.bacaberita.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.averoes.daff.bacaberita.DetailActivity
import com.averoes.daff.bacaberita.MainActivity
import com.averoes.daff.bacaberita.R
import com.averoes.daff.bacaberita.model.BeritaItem
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.util.ArrayList


/**
 * Created by daff on 05/02/19 at 9:08.
 */

class BeritaAdapter(//tempat untuk menampung context
        private val context: Context, var item_berita: ArrayList<BeritaItem>?)//inisialisasi
    : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //buat layout inflater
        val itemRow = LayoutInflater.from(parent.context).inflate(R.layout.item_berita, parent, false)

        //Kita hubungkan dengan view holder\

        return ViewHolder(itemRow)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.judul.text = item_berita!![position].judulBerita
        holder.penulis.text = item_berita!![position].penulis
        holder.tanggal.text = item_berita!![position].tanggalPosting

        val img_url = "http://192.168.95.104/portal berita/images/" + item_berita!![position].foto

        Glide.with(context).load(img_url).apply(RequestOptions()).into(holder.item_img)

        holder.itemView.setOnClickListener {
            // Mulai activity Detail
            val detailIntent = Intent(context, DetailActivity::class.java)
            // sisipkan data ke intent
            detailIntent.putExtra(DetailActivity.TITTLE, item_berita!![position].judulBerita)
            detailIntent.putExtra(DetailActivity.DATE, item_berita!![position].tanggalPosting)
            detailIntent.putExtra(DetailActivity.WRITER, item_berita!![position].penulis)
            detailIntent.putExtra(DetailActivity.IMG, img_url)
            detailIntent.putExtra(DetailActivity.CONTENT, item_berita!![position].isiBerita)

            context.startActivity(detailIntent)
        }

    }

    override fun getItemCount(): Int {
        return item_berita!!.size

    }


}
