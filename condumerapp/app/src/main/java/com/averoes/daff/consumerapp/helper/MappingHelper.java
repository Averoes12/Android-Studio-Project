package com.averoes.daff.consumerapp.helper;

import android.database.Cursor;

import com.averoes.daff.consumerapp.model.NoteItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DATE;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DESCRIP;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TITLE;

/**
 * Created by daff on 27/02/19 at 22:06.
 */

public class MappingHelper {

    public static ArrayList<NoteItem> mapCursorToArrayList (Cursor noteCursor){
        ArrayList<NoteItem>  note_list = new ArrayList<>();
        while (noteCursor.moveToNext()) {
            int id = noteCursor.getInt(noteCursor.getColumnIndexOrThrow(_ID));
            String title = noteCursor.getString(noteCursor.getColumnIndexOrThrow(TITLE));
            String description = noteCursor.getString(noteCursor.getColumnIndexOrThrow(DESCRIP));
            String date = noteCursor.getString(noteCursor.getColumnIndexOrThrow(DATE));
            note_list.add(new NoteItem(id, title, description, date));
        }
        return note_list;
    }
}
