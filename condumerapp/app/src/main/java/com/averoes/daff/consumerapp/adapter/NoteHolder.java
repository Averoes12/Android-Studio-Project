package com.averoes.daff.consumerapp.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

import com.averoes.daff.consumerapp.R;

/**
 * Created by daff on 27/02/19 at 21:30.
 */

class NoteHolder extends ViewHolder{

    TextView title, descrip, date;
    View rootView;

    public NoteHolder(View itemView) {
        super(itemView);
        rootView = itemView;
        title = itemView.findViewById(R.id.item_title);
        descrip = itemView.findViewById(R.id.item_descrip);
        date = itemView.findViewById(R.id.item_date);

    }
}
