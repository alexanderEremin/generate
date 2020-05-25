package ru.eremin.generate.data.generate.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Data ("
                + "id integer primary key autoincrement,"
                + "surname text,"
                + "name text,"
                + "jobplace text,"
                + "gender text,"
                + "area text,"
                + "avto text,"
                + "age text" +  ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
