package com.averoes.daff.consumerapp.CustomOnClick;

import android.view.View;

/**
 * Created by daff on 27/02/19 at 21:40.
 */

public class CustomOnClickListener implements View.OnClickListener{

    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v, position);

    }

    /**
     * Created by daff on 17/02/19 at 20:40.
     */

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
