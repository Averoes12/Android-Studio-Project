package com.averoes.daff.consumerapp;

import android.database.Cursor;

/**
 * Created by daff on 27/02/19 at 21:08.
 */

public interface LoadNoteCallback {
    void postExecute(Cursor notes);
}
