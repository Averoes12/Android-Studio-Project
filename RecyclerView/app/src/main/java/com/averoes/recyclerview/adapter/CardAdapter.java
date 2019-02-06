package com.averoes.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.averoes.recyclerview.R;
import com.averoes.recyclerview.listener.CustomOnItemClickListener;
import com.averoes.recyclerview.model.Hero;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {
    private Context context;
    private ArrayList<Hero> list_hero;

    public CardAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Hero> getList_hero() {
        return list_hero;
    }

    public void setList_hero(ArrayList<Hero> list_hero) {
        this.list_hero = list_hero;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view, parent, false);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardHolder cardHolder, int position) {

        Hero p = getList_hero().get(position);

        Glide.with(context).load(p.getPhoto())
                .apply(new RequestOptions().override(350, 550))
                .into(cardHolder.photo);

        cardHolder.name.setText(getList_hero().get(position).getName());
        cardHolder.from.setText(getList_hero().get(position).getFrom());

        cardHolder.btn_fav.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite " + getList_hero().get(position).getName(), Toast.LENGTH_SHORT).show();

            }
        }));

        cardHolder.btn_share.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {

                Toast.makeText(context, "Share " + getList_hero().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getList_hero().size();
    }
}
