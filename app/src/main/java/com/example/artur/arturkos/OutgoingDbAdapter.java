package com.example.artur.arturkos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Artur on 28.01.2018.
 */

public class OutgoingDbAdapter {
    private static  final String DATABASE_NAME = "OUTGOINGS_DATABASE.db";
    private static  final String OUTGOING_TABLE ="WYDATKI_TABLE";
    private static  final int DATABASE_VERSION = 200;
    private final Context mCtx;
    public static  String TAG = OutgoingDbAdapter.class.getSimpleName();

    private DatabaseHelper mDbHelper;
    SQLiteDatabase mDb;

    public static  final String KEY_ROWID = "_id";
    public static  final String DATA = "data";
    public static  final String TYP_ID = "typ_id";
    public static  final String NAZWA = "nazwa";
    public static  final String WARTOSC = "wartosc";

    public static final String[] OUTGOING_FIELDS = new String[]{
            KEY_ROWID,
            DATA,
            TYP_ID,
            NAZWA,
            WARTOSC
    };
    private static final String CREATE_TABLE_OUTGOING =
            "create table" + OUTGOING_TABLE + "("
            + KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATA + " text,"
            + TYP_ID + " INTEGER,"
            + NAZWA + " text,"
            + WARTOSC + " REAL"
            + ");";
    public class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context) {
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(CREATE_TABLE_OUTGOING);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Aktualizacja bazy danych z wersji "+ oldVersion+" na wersję "
            +newVersion + ", co spowoduje usunięcie wszystkich danych z bazy ");
            db.execSQL("DROP TABLE IF EXISTS " + OUTGOING_TABLE);
            onCreate(db);
        }
    }
    public OutgoingDbAdapter (Context ctx){
        this.mCtx = ctx;
    }
    public OutgoingDbAdapter open() throws SQLException{
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        if(mDbHelper != null)
            mDbHelper.close();
    }
    public void upgrade() throws SQLException{
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        mDbHelper.onUpgrade(mDb, 1, 0);
    }
    public long insertOutgoing(ContentValues initialValues){
        return mDb.insertWithOnConflict(OUTGOING_TABLE, null, initialValues, SQLiteDatabase.CONFLICT_IGNORE);
    }
    public boolean updateOutgoing(int id, ContentValues newValues){
        String[] selectionArgs = {String.valueOf(id)};
        return mDb.update(OUTGOING_TABLE, newValues, KEY_ROWID + "=?", selectionArgs) > 0;
    }
    public boolean deleteOutgoing(int id){
        String[] selectionArgs = {String.valueOf(id)};
        return mDb.delete(OUTGOING_TABLE, KEY_ROWID + "=?", selectionArgs)>0;
    }
    public Cursor getOutgoings(){
        return mDb.query(OUTGOING_TABLE, OUTGOING_FIELDS, null, null, null, null, null);
    }
    public static Outgoing getOutgoingFromCursor (Cursor cursor){
        Outgoing outgoing = new Outgoing();
        outgoing.mId = cursor.getInt(cursor.getColumnIndex(KEY_ROWID));
        outgoing.mData = cursor.getString(cursor.getColumnIndex(DATA));
        outgoing.mNazwa = cursor.getString(cursor.getColumnIndex(NAZWA));
        outgoing.mTyp_id = cursor.getString(cursor.getColumnIndex(TYP_ID));
        outgoing.mWartosc = cursor.getFloat(cursor.getColumnIndex(WARTOSC));
        return(outgoing);
    }
}
