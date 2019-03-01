package com.averoes.daff.listmahasiswa.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


import com.averoes.daff.listmahasiswa.model.MahasiswaModel;

import static android.provider.BaseColumns._ID;
import static com.averoes.daff.listmahasiswa.database.DatabaseConstract.MahasiswaColumn.NAME;
import static com.averoes.daff.listmahasiswa.database.DatabaseConstract.MahasiswaColumn.NIM;
import static com.averoes.daff.listmahasiswa.database.DatabaseConstract.TABLE_NAME;

import java.util.ArrayList;

/**
 * Created by daff on 21/02/19 at 18:19.
 */

public class MahasiswaHelper {


    /*
    * kelas ini berguna untuk memudahkan dalam penggunaan database mahasiswa*/

    private DatabaseHelper helper;
    private static MahasiswaHelper INSTANCE;

    private SQLiteDatabase database;

    public MahasiswaHelper(Context context){
        helper = new DatabaseHelper(context);
    }

    public static MahasiswaHelper getInstance(Context context){
        if (INSTANCE == null){
            synchronized (SQLiteOpenHelper.class){
                if (INSTANCE == null){
                    INSTANCE = new MahasiswaHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException{
        database = helper.getWritableDatabase();
    }

    public void close(){
        helper.close();
        if (database.isOpen()){
            database.close();
        }
    }
    /**
     * Gunakan method ini untuk mendapatkan semua data mahasiswa
     *
     */

    public ArrayList<MahasiswaModel> getAllData(){

        Cursor cursor = database.query(TABLE_NAME, null,null,null,null,
                null,
                _ID + " ASC",
                null);

        cursor.moveToFirst();

        ArrayList<MahasiswaModel> list = new ArrayList<>();
        MahasiswaModel mahasiswaModel;

        if (cursor.getCount() > 0){
            do {
                mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setId(cursor.getColumnIndexOrThrow(_ID));
                mahasiswaModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME)));
                mahasiswaModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM)));

                list.add(mahasiswaModel);
                cursor.moveToNext();

            }while (!cursor.isAfterLast());

        }

        cursor.close();
        return list;
    }

    /**
     * Gunakan method ini untuk query insert
     *
     * @param mahasiswaModel inputan model mahasiswa
     * @return id dari data mahasiswa yang baru saja dimasukkan
     */

    public long insert(MahasiswaModel mahasiswaModel){
        ContentValues values = new ContentValues();
        values.put(NAME, mahasiswaModel.getName());
        values.put(NIM, mahasiswaModel.getNim());
        return database.insert(TABLE_NAME, null, values);
    }

    /**
     * Gunakan method ini untuk memulai sesi query transaction
     */

    public void beginTransaction(){
        database.beginTransaction();
    }
    /**
     * Gunakan method ini jika query transaction berhasil, jika error jangan panggil method ini
     */

    public void setTransactionSucces(){
        database.setTransactionSuccessful();
    }

    public void endTransaction(){
        database.endTransaction();
    }

    /**
     * Gunakan method ini untuk query insert di dalam transaction
     *
     * @param mahasiswaModel inputan model mahasiswa
     */

    public void insertTransaction(MahasiswaModel mahasiswaModel){
        String sql = "INSERT INTO " + TABLE_NAME +" ("+
                DatabaseConstract.MahasiswaColumn.NAME+ ","+
                NIM+ ") VALUES (?, ?)";

        SQLiteStatement statement  = database.compileStatement(sql);
        statement.bindString(1,mahasiswaModel.getName());
        statement.bindString(2,mahasiswaModel.getNim());
        statement.execute();
        statement.clearBindings();
    }
}
