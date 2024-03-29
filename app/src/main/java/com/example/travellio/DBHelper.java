package com.example.travellio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    private Context context;
    private static final String DATABASE_NAME = "Travels.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "travels";
    private static final String COLUMN_ID = "travel_id";
    private static final String COLUMN_NAME = "travel_name";
    private static final String COLUMN_INFO = "travel_info";
    private static final String COLUMN_DATEFROM = "travel_datefrom";
    private static final String COLUMN_DATETO = "travel_dateto";

    private static final String TABLE_NAME2 = "travelsHistory";
    private static final String COLUMN_ID2 = "travel_idh";
    private static final String COLUMN_NAME2 = "travel_nameh";
    private static final String COLUMN_INFO2 = "travel_infoh";
    private static final String COLUMN_DATEFROM2 = "travel_datefromh";
    private static final String COLUMN_DATETO2 = "travel_datetoh";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_INFO + " TEXT, " +
                COLUMN_DATEFROM + " TEXT, " +
                COLUMN_DATETO + " TEXT);";
        db.execSQL(query);

        query = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_ID2 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME2 + " TEXT, " +
                COLUMN_INFO2 + " TEXT, " +
                COLUMN_DATEFROM2 + " TEXT, " +
                COLUMN_DATETO2 + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTravel(String name, String info, String travel_datefrom, String travel_dateto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_INFO, info);
        cv.put(COLUMN_DATEFROM, travel_datefrom);
        cv.put(COLUMN_DATETO, travel_dateto);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void addHistoryTravel(String name, String info, String travel_datefrom, String travel_dateto) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME2, name);
        cv.put(COLUMN_INFO2, info);
        cv.put(COLUMN_DATEFROM2, travel_datefrom);
        cv.put(COLUMN_DATETO2, travel_dateto);
        long result = db.insert(TABLE_NAME2, null, cv);
        if(result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataHistory() {
        String query = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}