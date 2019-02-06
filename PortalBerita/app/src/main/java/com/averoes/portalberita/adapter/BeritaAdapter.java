package com.averoes.portalberita.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.averoes.portalberita.model.BeritaItem;

import java.util.ArrayList;


public class BeritaAdapter extends RecyclerView.Adapter<Holder> {

    private Context context;
    private ArrayList<BeritaItem> beritaItem;

    public BeritaAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<BeritaItem> getBeritaItem() {
        return beritaItem;
    }

    public void setBeritaItem(ArrayList<BeritaItem> beritaItem) {
        this.beritaItem = beritaItem;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
