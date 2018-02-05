package com.example.artur.arturkos;

/**
 * Created by Artur on 01.02.2018.
 */

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.artur.arturkos.contentprovider.MyTodoContentProvider;
import com.example.artur.arturkos.database.TodoTable;

/*
 * TodoDetailActivity allows to enter a new todo item
 * or to change an existing
 */
public class TodoDetailActivity extends Activity {
    private Spinner typ_id;
    private EditText nazwa;
    private EditText data;
    private EditText wartosc;

    private Uri todoUri;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.todo_edit);

        typ_id = (Spinner) findViewById(R.id.type_id);
        nazwa = (EditText) findViewById(R.id.todo_edit_nazwa);
        data = (EditText) findViewById(R.id.todo_edit_data);
        wartosc = (EditText) findViewById(R.id.todo_edit_wartosc);
        Button confirmButton = (Button) findViewById(R.id.todo_edit_button);

        Bundle extras = getIntent().getExtras();

        // check from the saved Instance
        todoUri = (bundle == null) ? null : (Uri) bundle
                .getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);

        // Or passed from the other activity
        if (extras != null) {
            todoUri = extras
                    .getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);

            fillData(todoUri);
        }

        confirmButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (TextUtils.isEmpty(nazwa.getText().toString())) {
                    makeToast();
                } else {
                    setResult(RESULT_OK);
                    finish();
                }
            }

        });
    }

    private void fillData(Uri uri) {
//        String[] projection = { TodoTable.COLUMN_DATA,
//                TodoTable.COLUMN_TYP_ID, TodoTable.COLUMN_NAME};
        Cursor cursor = getContentResolver().query(uri, null, null, null,
                null);
        if (cursor != null) {
            cursor.moveToFirst();
            String category = cursor.getString(cursor
                    .getColumnIndexOrThrow(TodoTable.COLUMN_NAME));

            for (int i = 0; i < typ_id.getCount(); i++) {

                String s = (String) typ_id.getItemAtPosition(i);
                if (s.equalsIgnoreCase(category)) {
                    typ_id.setSelection(i);
                }
            }

            nazwa.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(TodoTable.COLUMN_NAME)));
            data.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(TodoTable.COLUMN_DATA)));
            wartosc.setText(cursor.getString(cursor
                    .getColumnIndexOrThrow(TodoTable.COLUMN_VALUE)));

            // always close the cursor
            cursor.close();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE, todoUri);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }

    private void saveState() {
        String type_id = (String) typ_id.getSelectedItem();
        String name = nazwa.getText().toString();
        String date = data.getText().toString();
        String val = wartosc.getText().toString();

        // only save if either summary or description
        // is available

        if (date.length() == 0 && name.length() == 0) {
            return;
        }

        ContentValues values = new ContentValues();
        values.put(TodoTable.COLUMN_NAME, name);
        values.put(TodoTable.COLUMN_DATA, date);
        values.put(TodoTable.COLUMN_TYP_ID, type_id);
        values.put(TodoTable.COLUMN_VALUE, val);

        if (todoUri == null) {
            // New todo
            todoUri = getContentResolver().insert(
                    MyTodoContentProvider.CONTENT_URI, values);
        } else {
            // Update todo
            getContentResolver().update(todoUri, values, null, null);
        }
    }

    private void makeToast() {
        Toast.makeText(TodoDetailActivity.this, "Please maintain a summary",
                Toast.LENGTH_LONG).show();
    }
}
