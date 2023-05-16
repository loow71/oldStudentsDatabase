package com.example.myprojcet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public void addStudent(Student student){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, student.getName());
        contentValues.put(KEY_LAST_NAME, student.getLast_name());
        contentValues.put(KEY_SURNAME, student.getSurname());
        contentValues.put(KEY_DATE, student.getDate());
        contentValues.put(KEY_GR, student.getGroup());

        database.insert(TABLE_STUDENTS, null, contentValues);
        database.close();
    }

    public Student getStudent(int id){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_STUDENTS, new String[] {KEY_ID, KEY_NAME, KEY_LAST_NAME, KEY_SURNAME, KEY_DATE, KEY_GR},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return student;
    }

    public int updateStudent(Student student){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY_NAME, student.getName());
        contentValues.put(KEY_LAST_NAME, student.getLast_name());
        contentValues.put(KEY_SURNAME, student.getSurname());
        contentValues.put(KEY_DATE, student.getDate());
        contentValues.put(KEY_GR, student.getGroup());

        return database.update(TABLE_STUDENTS, contentValues, KEY_ID + "=?", new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student){
        SQLiteDatabase database = this.getWritableDatabase();

        database.delete(TABLE_STUDENTS, KEY_ID + "=?", new String[]{String.valueOf(student.getId())});
        database.close();
    }
}
