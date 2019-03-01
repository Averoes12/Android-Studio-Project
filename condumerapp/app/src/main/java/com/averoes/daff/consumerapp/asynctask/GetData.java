package com.averoes.daff.consumerapp.asynctask;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.averoes.daff.consumerapp.LoadNoteCallback;

import java.lang.ref.WeakReference;

import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.CONTENT_URI;

/**
 * Created by daff on 27/02/19 at 22:00.
 */

public class GetData extends AsyncTask<Void, Void, Cursor>{
    private  final WeakReference<Context> weakContext;
    private final WeakReference<LoadNoteCallback> weakCallback;

    public GetData(Context context, LoadNoteCallback callback) {
        this.weakContext = new WeakReference<>(context);
        this.weakCallback = new WeakReference<>(callback);
    }

    @Override
    protected Cursor doInBackground(Void... voids) {
        return weakContext.get().getContentResolver().query(CONTENT_URI, null, null,null,null);
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        weakCallback.get().postExecute(cursor);
    }
}
