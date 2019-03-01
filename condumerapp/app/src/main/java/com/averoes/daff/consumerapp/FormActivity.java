package com.averoes.daff.consumerapp;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.averoes.daff.consumerapp.model.NoteItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.CONTENT_URI;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DATE;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.DESCRIP;
import static com.averoes.daff.consumerapp.database.DataConstract.NoteColumns.TITLE;

public class FormActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText in_title, in_descrip;

    private Button btn_submit;
    private NoteItem noteItem = null;
    private boolean isUpdate = false;
    private ContentResolver contentResolver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        in_title = findViewById(R.id.input_title);
        in_descrip = findViewById(R.id.input_desc);
        btn_submit = findViewById(R.id.btn_submit);
        contentResolver = getContentResolver();
        btn_submit.setOnClickListener(this);

        Uri uri = getIntent().getData();

        if (uri != null){
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null){
                if (cursor.moveToFirst()) noteItem = new NoteItem(cursor);
                cursor.close();
            }
        }

        String actionBarTitle;
        String btn_title;



        if (noteItem != null) {
            isUpdate = true;
            actionBarTitle = "Edit Note";
            btn_title = "Update";

            in_title.setText(noteItem.getTitle());
            in_descrip.setText(noteItem.getDesc());

        } else {
            actionBarTitle = "Add Note";
            btn_title = "Save";
        }
        btn_submit.setText(btn_title);
        getSupportActionBar().setTitle(actionBarTitle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            String title = in_title.getText().toString().trim();
            String descr = in_descrip.getText().toString().trim();

            boolean isEmpty = false;
//Jika fieldnya masih kosong maka tampilkan error

            if (TextUtils.isEmpty(title)) {
                isEmpty = true;
                in_title.setError("Field Must Be Filled");
            }

            if (!isEmpty) {

                // Gunakan contentvalues untuk menampung data
                ContentValues values = new ContentValues();
                values.put(TITLE, title);
                values.put(DESCRIP, descr);
                values.put(DATE, getCurrentDate());

                if (isUpdate) {
                    Uri uri = getIntent().getData();
                    contentResolver.update(uri, values, null, null);
                    Toast.makeText(FormActivity.this, "One item has been changed succesfully", Toast.LENGTH_SHORT).show();

                } else {

                    contentResolver.insert(CONTENT_URI, values);
                    Toast.makeText(FormActivity.this, "One item has been saved succesfully", Toast.LENGTH_SHORT).show();
                }
                contentResolver.notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(),this));
                finish();
            }
        }
    }

    private String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isUpdate) {
            getMenuInflater().inflate(R.menu.menu_form, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }else if (item.getItemId() == R.id.action_delete){
            showAlertDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void showAlertDialog() {
        String dialogMessage = "are you sure do you want to delete this file ?";
        String dialogTitle = "Delete Note";
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(dialogTitle);
        alertBuilder
                .setMessage(dialogMessage)
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                      Uri uri = getIntent().getData();
                      contentResolver.delete(uri, null, null);
                      contentResolver.notifyChange(CONTENT_URI, new MainActivity.DataObserver(new Handler(), FormActivity.this));
                      Toast.makeText(FormActivity.this, "One item has been deleted successfully", Toast.LENGTH_SHORT).show();
                      finish();


                    }
                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.show();

    }
}
