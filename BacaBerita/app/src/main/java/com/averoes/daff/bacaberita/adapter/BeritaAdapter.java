package com.averoes.daff.bacaberita.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.averoes.daff.bacaberita.DetailActivity;
import com.averoes.daff.bacaberita.MainActivity;
import com.averoes.daff.bacaberita.R;
import com.averoes.daff.bacaberita.model.BeritaItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;


/**
 * Created by daff on 05/02/19 at 9:08.
 */

public class BeritaAdapter extends RecyclerView.Adapter<ViewHolder> {
    //tempat untuk menampung context
    private Context context;
    private ArrayList<BeritaItem> item_berita;

    public BeritaAdapter(Context context, ArrayList<BeritaItem> list_berita) {
        //inisialisasi
        this.context = context;
        this.item_berita = list_berita;
    }

    public ArrayList<BeritaItem> getItem_berita() {
        return item_berita;
    }

    public void setItem_berita(ArrayList<BeritaItem> item_berita) {
        this.item_berita = item_berita;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //buat layout inflater
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_berita, parent, false);

        //Kita hubungkan dengan view holder\
        ViewHolder holder = new ViewHolder(itemRow);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.judul.setText(item_berita.get(position).getJudulBerita());
        holder.penulis.setText(item_berita.get(position).getPenulis());
        holder.tanggal.setText(item_berita.get(position).getTanggalPosting());

       final String img_url = "http://172.20.30.19/portal berita/images/" + item_berita.get(position).getFoto();

            Glide.with(context).load(img_url).apply(new RequestOptions()).into(holder.item_img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mulai activity Detail
                Intent detailIntent = new Intent(context, DetailActivity.class);
                // sisipkan data ke intent
                detailIntent.putExtra(DetailActivity.TITTLE, item_berita.get(position).getJudulBerita());
                detailIntent.putExtra(DetailActivity.DATE, item_berita.get(position).getTanggalPosting());
                detailIntent.putExtra(DetailActivity.WRITER, item_berita.get(position).getPenulis());
                detailIntent.putExtra(DetailActivity.IMG, img_url);
                detailIntent.putExtra(DetailActivity.CONTENT, item_berita.get(position).getIsiBerita());

                context.startActivity(detailIntent);
            }});

    }
    @Override
    public int getItemCount() {
        return item_berita.size();

    }





}
