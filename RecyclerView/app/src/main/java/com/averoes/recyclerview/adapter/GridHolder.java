package com.averoes.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.averoes.recyclerview.R;

public class GridHolder extends RecyclerView.ViewHolder {
     ImageView imgPhoto;

    public GridHolder(@NonNull View itemView) {
        super(itemView);

        imgPhoto = itemView.findViewById(R.id.item_img);
    }
}
