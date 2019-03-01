package com.averoes.daff.consumerapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daff on 27/02/19 at 21:47.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public static String DATABASE_NAME = "dbnoteapp";
    private static int DATABASE_VERSION = 1;

    //String disini adalah perintah untuk membuat table pada sqllite
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s " +
                    "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL )",
            DataConstract.NoteColumns.TABLE_NAME, //nama table nya

            //di bawah ini adalah isi yang ada di dalam table pada database kita
            DataConstract.NoteColumns._ID, // kolom id
            DataConstract.NoteColumns.TITLE, // kolom title
            DataConstract.NoteColumns.DESCRIP, // kolom deskripsi
            DataConstract.NoteColumns.DATE); //kolom tanggal

    //construct ini berfungsi untuk membuat membuka atau mengatur database
    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

/* pada methode ini database sqlite akan dibuat sesuai dengan data-data diatas */
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    /**
     * Method onUpgrade akan di panggil ketika terjadi perbedaan versi
     * Gunakan method onUpgrade untuk melakukan proses migrasi data
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /**
         *Drop table tidak dianjurkan ketika proses migrasi terjadi dikarenakan data user akan hilang,
         *Akan tetapi untuk mempermudah, maka drop table tetap dilakukan untuk menghindari error
         */

        db.execSQL("DROP TABLE IF EXISTS " + DataConstract.NoteColumns.TABLE_NAME);
        onCreate(db);
    }
}
