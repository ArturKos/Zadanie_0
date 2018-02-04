package com.example.artur.arturkos;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.CursorAdapter;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView name = (TextView) view.findViewById(R.id.label1);
        TextView date = (TextView) view.findViewById(R.id.label2);
        TextView sum = (TextView) view.findViewById(R.id.label3);
        TextView desc = (TextView) view.findViewById(R.id.label4);
        // Extract properties from cursor
        String category = cursor.getString(cursor.getColumnIndexOrThrow("category"));
        String summary = cursor.getString(cursor.getColumnIndexOrThrow("summary"));
        String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
        double wartosc = cursor.getInt(cursor.getColumnIndexOrThrow("wartosc"));
        // Populate fields with extracted properties
        name.setText(category);
        date.setText(description);
        sum.setText(summary);
        desc.setText(Double.toString(wartosc));
    }
}
