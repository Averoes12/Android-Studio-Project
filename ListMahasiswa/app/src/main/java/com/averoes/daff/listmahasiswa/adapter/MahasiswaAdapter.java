package com.averoes.daff.listmahasiswa.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.averoes.daff.listmahasiswa.R;
import com.averoes.daff.listmahasiswa.model.MahasiswaModel;

import java.util.ArrayList;

/**
 * Created by daff on 21/02/19 at 20:32.
 */

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.ViewHolder>{

    public ArrayList<MahasiswaModel> list_mahasiswa = new ArrayList<>();

    public MahasiswaAdapter() {
    }

    public void setList_mahasiswa(ArrayList<MahasiswaModel> list) {
        if (list_mahasiswa.size() > 0) {
            this.list_mahasiswa = list;
        }
        this.list_mahasiswa.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MahasiswaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.ViewHolder holder, int position) {
        holder.text_nim.setText(list_mahasiswa.get(position).getNim());
        holder.text_name.setText(list_mahasiswa.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list_mahasiswa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView text_nim, text_name;
        public ViewHolder(View itemView) {
            super(itemView);

            text_name = itemView.findViewById(R.id.txt_nama);
            text_nim = itemView.findViewById(R.id.txt_nim);
        }
    }
}
