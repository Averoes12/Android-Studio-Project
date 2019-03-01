package com.averoes.daff.consumerapp.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by daff on 27/02/19 at 20:18.
 */

public class DataConstract {
    public static final String CONTENT_PROVIDER_AUTHORITY = "com.averoes.daff.notesapp";
    private static final String SCHEME = "content";

    public DataConstract() {
    }

    /**
     * dikelas ini berisi data-data yang akan digunakan untuk membuat database
     */
    public static final class NoteColumns implements BaseColumns {


        public final static String TABLE_NAME = "note";//nama table

        //kolom-kolom yang ada pada table diatas
        public static String TITLE = "title"; //kolom title
        public static String DESCRIP = "description"; //kolom deskripsi
        public static String DATE = "date"; //kolom tanggal

//Base content yang digunakan untuk akses content provider

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(CONTENT_PROVIDER_AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    /**
     * Digunakan untuk mempermudah akses data di dalam cursor dengan parameter nama column
     */

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong(cursor.getColumnIndex(columnName));
    }

}
