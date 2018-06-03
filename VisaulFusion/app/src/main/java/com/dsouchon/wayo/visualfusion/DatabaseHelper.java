package com.dsouchon.wayo.visualfusion;

/**
 * Created by Daniel on 2018/06/03.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "GLOBALS";

    // Table columns
    public static final String ID = "id";
    public static final String KEY = "key";
    public static final String VALUE = "value";

    // Database Information
    static final String DB_NAME = "DSOUCHON.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY + " TEXT NOT NULL, " + VALUE + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
