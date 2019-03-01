package com.averoes.daff.consumerapp;

import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.averoes.daff.consumerapp.adapter.Adapter;
import com.averoes.daff.consumerapp.asynctask.GetData;
import com.averoes.daff.consumerapp.model.NoteItem;
import java.util.ArrayList;

import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.CONTENT_URI;
import static com.averoes.daff.consumerapp.helper.MappingHelper.mapCursorToArrayList;

public class MainActivity extends AppCompatActivity implements LoadNoteCallback{
    private Adapter adapter;
    private DataObserver dataObserver;
    RecyclerView recyclerView;
    HandlerThread handlerThread;
    Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Consumer App");

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
        dataObserver = new DataObserver(handler, this);

        getContentResolver().registerContentObserver(CONTENT_URI, true, dataObserver);
        new GetData(this, this).execute();

    }

    @Override
    public void postExecute(Cursor notes) {

        ArrayList<NoteItem> list_note = mapCursorToArrayList(notes);
        if (list_note.size()> 0){
            adapter.setList_item(list_note);
            Log.d("STATE", "Data Found");
        }else {
            adapter.setList_item(new ArrayList<NoteItem>());
            Toast.makeText(this, "Nothing data at this time", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

     public static class DataObserver extends ContentObserver{

        final Context context;
         /**
          * Creates a content observer.
          *
          * @param handler The handler to run {@link #onChange} on, or null if none.
          */
         public DataObserver(Handler handler, Context context) {
             super(handler);
             this.context = context;

         }

         @Override
         public void onChange(boolean selfChange) {
             super.onChange(selfChange);
             new GetData(context, (MainActivity) context).execute();
         }
     }
}
