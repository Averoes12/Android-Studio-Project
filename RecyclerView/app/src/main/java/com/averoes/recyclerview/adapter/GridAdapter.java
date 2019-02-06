package com.averoes.recyclerview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.averoes.recyclerview.R;
import com.averoes.recyclerview.model.Hero;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridHolder> {
    private Context context;
    private ArrayList<Hero> list_hero;

    public GridAdapter(Context context) {

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
    public GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent, false);

        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder gridHolder, int position) {

        Glide.with(context).load(getList_hero().get(position).getPhoto())
                .apply(new RequestOptions().override(350,559))
                .into(gridHolder.imgPhoto);

    }

    @Override
    public int getItemCount() {

        return getList_hero().size();
    }
}
