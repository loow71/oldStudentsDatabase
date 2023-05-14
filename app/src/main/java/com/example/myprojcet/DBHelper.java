package com.example.myprojcet;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "studentsDb";
    public static final String TABLE_STUDENTS = "students";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LAST_NAME = "last_name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_DATE = "date";
    public static final String KEY_GR = "gr";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_STUDENTS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_LAST_NAME
                + " text," + KEY_SURNAME + " text," + KEY_DATE + " date," + KEY_GR + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_STUDENTS);

        onCreate(db);
    }
}
