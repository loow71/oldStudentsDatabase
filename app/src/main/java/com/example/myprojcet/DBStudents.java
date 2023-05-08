package com.example.myprojcet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBStudents {
    private static final String DATABASE_NAME = "students.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "students";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_SURNAME = "surname";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";
    private static final String COLUMN_GROUP = "gr";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_NAME = 1;
    private static final int NUM_COLUMN_LAST_NAME = 2;
    private static final int NUM_COLUMN_SURNAME = 3;
    private static final int NUM_COLUMN_DATE_OF_BIRTH = 4;
    private static final int NUM_COLUMN_GROUP = 5;

    private SQLiteDatabase sDataBase;

    public DBStudents(Context context) {
        OpenHelper sOpenHelper = new OpenHelper(context);
        sDataBase = sOpenHelper.getWritableDatabase();
    }

    public long insert(String name, String l_name, String s_name, String date, String gr) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LAST_NAME, l_name);
        cv.put(COLUMN_SURNAME, s_name);
        cv.put(COLUMN_DATE_OF_BIRTH, date);
        cv.put(COLUMN_GROUP, gr);
        return sDataBase.insert(TABLE_NAME, null, cv);
    }

    public int update(Students sd) {
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_NAME, sd.getName());
        cv.put(COLUMN_LAST_NAME, sd.getLast_name());
        cv.put(COLUMN_SURNAME, sd.getSurname());
        cv.put(COLUMN_DATE_OF_BIRTH, sd.getDate_of_birth());
        cv.put(COLUMN_GROUP, sd.getGr());
        return sDataBase.update(TABLE_NAME, cv, COLUMN_ID + " = ?",new String[] { String.valueOf(sd.getId())});
    }

    public void deleteAll() {
        sDataBase.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        sDataBase.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(id) });
    }

    public Students select(long id) {
        Cursor sCursor = sDataBase.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        sCursor.moveToFirst();
        String name = sCursor.getString(NUM_COLUMN_NAME);
        String last_name = sCursor.getString(NUM_COLUMN_LAST_NAME);
        String surname = sCursor.getString(NUM_COLUMN_SURNAME);
        String date = sCursor.getString(NUM_COLUMN_DATE_OF_BIRTH);
        String gr = sCursor.getString(NUM_COLUMN_GROUP);
        return new Students(id, name, last_name, surname, date, gr);
    }

    public ArrayList<Students> selectAll() {
        Cursor sCursor = sDataBase.query(TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Students> arr = new ArrayList<Students>();
        sCursor.moveToFirst();
        if (!sCursor.isAfterLast()) {
            do {
                long id = sCursor.getLong(NUM_COLUMN_ID);
                String name = sCursor.getString(NUM_COLUMN_NAME);
                String last_name = sCursor.getString(NUM_COLUMN_LAST_NAME);
                String surname = sCursor.getString(NUM_COLUMN_SURNAME);
                String date = sCursor.getString(NUM_COLUMN_DATE_OF_BIRTH);
                String gr = sCursor.getString(NUM_COLUMN_GROUP);
                arr.add(new Students(id, name, last_name, surname, date, gr));
            } while (sCursor.moveToNext());
        }
        return arr;
    }

    private class OpenHelper extends SQLiteOpenHelper {

        OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_SURNAME + " TEXT, "+
                    COLUMN_DATE_OF_BIRTH + " TEXT, "+
                    COLUMN_GROUP + " TEXT);";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

}
