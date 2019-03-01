package com.averoes.daff.consumerapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.averoes.daff.consumerapp.CustomOnClick.CustomOnClickListener;
import com.averoes.daff.consumerapp.FormActivity;
import com.averoes.daff.consumerapp.R;
import com.averoes.daff.consumerapp.model.NoteItem;

import java.util.ArrayList;

import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.CONTENT_URI;

/**
 * Created by daff on 27/02/19 at 20:47.
 */

public class Adapter extends RecyclerView.Adapter<NoteHolder>{

    private final ArrayList<NoteItem> list_item = new ArrayList<>();
    private Activity activity;

    public Adapter(Activity activity) {
        this.activity = activity;
    }

    public ArrayList<NoteItem> getList_item() {
        return list_item;
    }

    public void setList_item(ArrayList<NoteItem> list_note){
        this.list_item.clear();
        this.list_item.addAll(list_note);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        holder.title.setText(getList_item().get(position).getTitle());
        holder.descrip.setText(getList_item().get(position).getDesc());
        holder.title.setText(getList_item().get(position).getDate());
        holder.itemView.setOnClickListener(new CustomOnClickListener(position, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormActivity.class);

                Uri uri = Uri.parse(CONTENT_URI + "/" + getList_item().get(position).getId());
                intent.setData(uri);
                activity.startActivity(intent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return list_item.size();
    }
}
