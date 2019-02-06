package com.averoes.myloader;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;


import de.hdodenhof.circleimageview.CircleImageView;

public class ContactAdapter extends CursorAdapter {

    public ContactAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row_contact,parent,false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {

        if (cursor != null){
            TextView name = view.findViewById(R.id.item_name);
            CircleImageView imgUser = view.findViewById(R.id.img_item_user);
            RelativeLayout rlItem = view.findViewById(R.id.rl_item);
            imgUser.setImageResource(R.drawable.account);

            name.setText(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME)));

            if (cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI))!= null){

                imgUser.setImageURI(Uri.parse(cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.PHOTO_URI))));
            }else{
                imgUser.setImageResource(R.drawable.account);
            }

        }
    }
}
