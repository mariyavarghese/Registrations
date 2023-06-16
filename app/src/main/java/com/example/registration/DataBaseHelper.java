package com.example.registration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "SignLog.db";


    public DataBaseHelper(@Nullable Context context) {
        super(context, "SignLog.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(name TEXT primary key,gender TEXT,status TEXT,guard TEXT,blood TEXT,age TEXT,address TEXT,category TEXT,date TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String name, String gender, String status, String guard, String blood, String age, String address, String category, String date, String password) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("status", status);
        contentValues.put("guard", guard);
        contentValues.put("blood", blood);
        contentValues.put("age", age);
        contentValues.put("address", address);
        contentValues.put("category", category);
        contentValues.put("date", date);
        contentValues.put("password", password);
        long result = MyDatabase.insert("users", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean checkName(String name){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where name = ?", new String[]{name});
        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
