package com.averoes.daff.listmahasiswa.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by daff on 21/02/19 at 18:06.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "data_mahasiswa";
    private static int DATABASE_VERSION = 1;
    private static String CREATE_TABLE = "create table " + DatabaseConstract.TABLE_NAME +
            "(" + DatabaseConstract.MahasiswaColumn._ID + " integer primary key autoincrement, "+
            DatabaseConstract.MahasiswaColumn.NAME + " text not null, " +
            DatabaseConstract.MahasiswaColumn.NIM + " text not null);";

    private static String DROP_TABLE = "DROP IF TABLE EXIST";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE + DatabaseConstract.TABLE_NAME);
        onCreate(db);

    }
}
