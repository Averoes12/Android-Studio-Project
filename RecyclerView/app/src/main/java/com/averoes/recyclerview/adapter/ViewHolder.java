package com.averoes.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.averoes.recyclerview.R;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView name, from;
    ImageView photo;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.name_hero);
        from = itemView.findViewById(R.id.descrip);
        photo = itemView.findViewById(R.id.img_profile);
    }
}
