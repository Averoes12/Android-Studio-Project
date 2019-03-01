package com.averoes.daff.listmahasiswa.database;

import android.provider.BaseColumns;

/**
 * Created by daff on 21/02/19 at 18:04.
 */

public class DatabaseConstract {

    public static String TABLE_NAME = "table_mahasiswa";

    static final class MahasiswaColumn implements BaseColumns {

        public static String NAME = "nama";
        public static String NIM = "nim";
    }
}
