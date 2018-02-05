package com.example.artur.arturkos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CursorAdapter;


/**
 * Created by Artur on 04.02.2018.
 */

public class TodoCursorAdapter extends CursorAdapter {
    public TodoCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.todo_row, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template

        TextView name = (TextView) view.findViewById(R.id.label3nazwa);
        TextView date = (TextView) view.findViewById(R.id.label1data);
        TextView type = (TextView) view.findViewById(R.id.label2typ);
        TextView value = (TextView) view.findViewById(R.id.label4wartosc);
        // Extract properties from cursor
        String nazwa = cursor.getString(cursor.getColumnIndexOrThrow("nazwa"));
        String typ_id = cursor.getString(cursor.getColumnIndexOrThrow("typ_id"));
        String data = cursor.getString(cursor.getColumnIndexOrThrow("data"));
        String wartosc = cursor.getString(cursor.getColumnIndexOrThrow("wartosc"));
        // Populate fields with extracted properties
        name.setText(nazwa);
        date.setText(data);
        type.setText(typ_id);
        value.setText(wartosc);
    }
}
