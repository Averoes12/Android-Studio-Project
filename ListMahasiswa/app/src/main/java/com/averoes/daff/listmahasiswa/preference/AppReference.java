package com.averoes.daff.listmahasiswa.preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by daff on 21/02/19 at 20:48.
 */

//Class ini digunakan untuk membantu menyimpan data shared preference

public class AppReference {
    private static final String PREFS_NAME = "Mahasiswa";
    private static final String APP_FIRST_RUN = "first run";
    private SharedPreferences preferences;

    public AppReference(Context context) {
        preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void setFirstRun(Boolean input){
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(APP_FIRST_RUN, input);
        editor.apply();
    }

    public boolean getFirstRun(){
        return preferences.getBoolean(APP_FIRST_RUN, true);
    }
}
