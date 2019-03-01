package com.averoes.daff.consumerapp.povider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.averoes.daff.consumerapp.MainActivity;
import com.averoes.daff.consumerapp.database.NoteHelper;

import static com.averoes.daff.consumerapp.database.DataConstract.CONTENT_PROVIDER_AUTHORITY;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.CONTENT_URI;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TABLE_NAME;

/**
 * Created by daff on 27/02/19 at 21:43.
 */

public class NoteProvider extends ContentProvider {


    /**
     * Integer digunakan sebagai identifier antara select all sama select by id
     */

    private static final int NOTE = 1;
    private static final int NOTE_ID = 2;

    private static final UriMatcher mathcer = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        // content://com.averoes.daff.notesapp/note
        mathcer.addURI(CONTENT_PROVIDER_AUTHORITY, TABLE_NAME, NOTE);

        // content://com.averoes.daff.notesapp/note/id
        mathcer.addURI("content://com.averoes.daff.notesapp/note/id", TABLE_NAME + "/#", NOTE_ID);
    }

    private NoteHelper helper;

    @Override
    public boolean onCreate() {
        helper = NoteHelper.getInstance(getContext());
        return true;
    }

    /**
     * Method query digunakan ketika ingin menjalankan query Select
     * Return cursor
     */

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        helper.open();
        Cursor cursor;
        switch (mathcer.match(uri)) {
            case NOTE:
                cursor = helper.queryProvider();
                break;
            case NOTE_ID:
                cursor = helper.queryByIdProvider(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {
        helper.open();
        long added;
        switch (mathcer.match(uri)) {
            case NOTE:
                added = helper.insertProvider(values);
                break;
            default:
                added = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));

        return Uri.parse(CONTENT_URI + "/" + added);
    }

    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        helper.open();
        int update;
        switch (mathcer.match(uri)) {
            case NOTE_ID:
                update = helper.updateProvider(uri.getLastPathSegment(), values);
                break;
            default:
                update = 0;
                break;
        }

        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));

        return update;
    }

    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {
        helper.open();
        int delete;
        switch (mathcer.match(uri)) {
            case NOTE_ID:
                delete = helper.deleteProvider(uri.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }
        getContext().getContentResolver().notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), getContext()));
        return delete;
    }
}

