package com.averoes.daff.consumerapp.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DATE;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DESCRIP;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TITLE;
import static com.averoes.daff.consumerapp.database.DataConstract.getColumnInt;
import static com.averoes.daff.consumerapp.database.DataConstract.getColumnString;

/**
 * Created by daff on 27/02/19 at 20:49.
 */

public class NoteItem implements Parcelable{

    private int id;
    private String title, desc, date;

    public NoteItem(int id, String tittle, String descrip, String date) {

    }

    public NoteItem() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NoteItem(Parcel in) {
        id = in.readInt();
        title = in.readString();
        desc = in.readString();
        date = in.readString();
    }

    public NoteItem(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.title = getColumnString(cursor, TITLE);
        this.desc = getColumnString(cursor, DESCRIP);
        this.date = getColumnString(cursor, DATE);

    }

    public static final Creator<NoteItem> CREATOR = new Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel in) {
            return new NoteItem(in);
        }

        @Override
        public NoteItem[] newArray(int size) {
            return new NoteItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(desc);
        dest.writeString(date);
    }
}
