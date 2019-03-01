package com.averoes.daff.consumerapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.averoes.daff.consumerapp.model.NoteItem;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DATE;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DESCRIP;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TABLE_NAME;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TITLE;

/**
 * Created by daff on 27/02/19 at 21:45.
 */

public class NoteHelper {
    private static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static NoteHelper INSTANCE;

    private static SQLiteDatabase database;

    private NoteHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);

    }

    public static NoteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen()) {
            database.close();
        }
    }


    /*
    METHOD DIBAWAH INI UNTUK QUERY YANG LAMA ATAU UNTUK PROJECT SQLITE
     */


    /**
     * Methode untuk mengambil data
     */

    public ArrayList<NoteItem> getAllNotes() {

        ArrayList<NoteItem> arrayList = new ArrayList<>();

        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                _ID + " ASC",
                null);
        cursor.moveToFirst();

        NoteItem note;
        if (cursor.getCount() > 0) {
            do {
                note = new NoteItem();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIP)));
                note.setDate(cursor.getString(cursor.getColumnIndexOrThrow(DATE)));

                arrayList.add(note);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    /**
     * Gunakan method ini untuk getAllNotes updateNote
     * Disini terjadi proses CRUD
     *
     * @param note model note yang akan diubah
     * @return int jumlah dari row yang ter-updateNote, jika tidak ada yang diupdate maka nilainya 0
     */

// Methode ketika user mengubah atau mengedit file yang ada di dalam database
    public long updateNote(NoteItem note) {
        ContentValues values = new ContentValues();
        values.put(TITLE, note.getTitle());
        values.put(DESCRIP, note.getDesc());
        values.put(DATE, note.getDate());

        return database.update(DATABASE_TABLE, values, _ID + "= '" + note.getId() + "'", null);
    }

    /**
     * Methode untuk memamsukkan data ke dalam database dari inputan user
     */

    public long insertNote(NoteItem note) {
        /* Content Values hampir sama seperti dengan array list
        digunakan untuk menyimpan kumpulan data */

        ContentValues values = new ContentValues();
        values.put(TITLE, note.getTitle());
        values.put(DESCRIP, note.getDesc());
        values.put(DATE, note.getDate());

        return database.insert(DATABASE_TABLE, null, values);
    }

    public int deleteNote(int id) {
        return database.delete(TABLE_NAME, _ID + " = '" + id + "'", null);
    }

     /*
    METHOD DI BAWAH INI ADALAH QUERY UNTUK CONTENT PROVIDER
    NILAI BALIK CURSOR
    */

    /**
     * Ambil data dari note berdasarakan parameter id
     * Gunakan method ini untuk ambil data di dalam provider
     *
     * @param id id note yang dicari
     * @return cursor hasil query
     */

    public Cursor queryByIdProvider(String id){
        return database.query(DATABASE_TABLE,
                null,
                _ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null);
    }

    /**
     * Ambil data dari semua note yang ada di dalam database
     * Gunakan method ini untuk ambil data di dalam provider
     *
     * @return cursor hasil query
     */

    public Cursor queryProvider() {
        return database.query(DATABASE_TABLE
                , null
                , null
                , null
                , null
                , null
                , _ID + " ASC");
    }

    /**
     * Simpan data ke dalam database
     * Gunakan method ini untuk query insert di dalam provider
     *
     * @param values nilai data yang akan di simpan
     * @return long id dari data yang baru saja di masukkan
     */

    public long insertProvider(ContentValues values){
        return database.insert(DATABASE_TABLE, null, values);
    }

    /**
     * Update data dalam database
     *
     * @param id     data dengan id berapa yang akan di update
     * @param values nilai data baru
     * @return int jumlah data yang ter-update
     */

    public int updateProvider(String id, ContentValues values){
        return database.update(DATABASE_TABLE, values, _ID + " = ?", new String[]{id});
    }

    /**
     * Delete data dalam database
     *
     * @param id data dengan id berapa yang akan di delete
     * @return int jumlah data yang ter-delete
     */

    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }
}
