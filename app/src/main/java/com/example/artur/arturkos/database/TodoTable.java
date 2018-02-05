package com.example.artur.arturkos.database;

/**
 * Created by Artur on 01.02.2018.
 */

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class TodoTable {

    // Database table
    public static final String TABLE_OUTGOING = "wydatek";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nazwa";
    public static final String COLUMN_DATA = "data";
    public static final String COLUMN_TYP_ID = "type_id";
    public static final String COLUMN_VALUE = "wartosc";

    public static final String TABLE_TYPE = "typ";
    public static final String COLUMN_TYPE_ID = "_id";
    public static final String COLUMN_TYPE_NAME = "nazwa";
    // Database creation SQL statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_OUTGOING
            + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " text not null, "
            + COLUMN_DATA + " text not null, "
            + COLUMN_TYP_ID
            + " text not null, "
            + COLUMN_VALUE
            + " text not null"
            + ");";

    private static final String DATABASE_CREATE_TYPE = "create table "
            + TABLE_TYPE
            + "("
            + COLUMN_TYPE_ID + " integer primary key autoincrement, "
            + COLUMN_TYPE_NAME + " text not null"
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE_TYPE);
//        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTGOING);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(TodoTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
//        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTGOING);
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTGOING + ";");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE + ";");
        onCreate(database);
    }
}
