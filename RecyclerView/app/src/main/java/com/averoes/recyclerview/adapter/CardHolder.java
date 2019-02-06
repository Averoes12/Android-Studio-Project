package com.averoes.recyclerview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.averoes.recyclerview.R;

public class CardHolder extends RecyclerView.ViewHolder {
    TextView name, from;
    ImageView photo;
    Button btn_fav, btn_share;

    public CardHolder(@NonNull View itemView) {
        super(itemView);

        photo = itemView.findViewById(R.id.item_img);
        name = itemView.findViewById(R.id.item_name);
        from = itemView.findViewById(R.id.desc);
        btn_fav = itemView.findViewById(R.id.btn_fav);
        btn_share = itemView.findViewById(R.id.btn_share);
    }
}
