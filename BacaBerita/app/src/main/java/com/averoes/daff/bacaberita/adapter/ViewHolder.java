package com.averoes.daff.bacaberita.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.averoes.daff.bacaberita.R;

/**
 * Created by daff on 05/02/19.
 */

public class ViewHolder extends RecyclerView.ViewHolder {
    ImageView item_img;
    TextView judul, penulis, tanggal;

    public ViewHolder(View itemView) {
        super(itemView);

        item_img = itemView.findViewById(R.id.item_image);
        judul = itemView.findViewById(R.id.item_judul);
        penulis = itemView.findViewById(R.id.item_penulis);
        tanggal = itemView.findViewById(R.id.item_tanggal);
    }
}
