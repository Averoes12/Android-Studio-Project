package com.averoes.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.averoes.recyclerview.HeroesData;
import com.averoes.recyclerview.MainActivity;
import com.averoes.recyclerview.R;
import com.averoes.recyclerview.model.Hero;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<ViewHolder> {
    private ArrayList<Hero> list_hero;
    private Context context;

    public HeroAdapter(Context context) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent,false);
        return new ViewHolder(itemRow);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(getList_hero().get(position).getName());
        holder.from.setText(getList_hero().get(position).getFrom());

        Glide.with(context).load(getList_hero().get(position).getPhoto()).apply(new RequestOptions().override(55, 55)).into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return getList_hero().size();
    }
}