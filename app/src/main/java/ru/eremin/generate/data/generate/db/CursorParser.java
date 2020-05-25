package ru.eremin.generate.data.generate.db;

import android.database.Cursor;

import java.util.ArrayList;

import ru.eremin.generate.data.managers.ListManager;

public class CursorParser {
    /**
     *
     * Возвращает полученные данные из БД
     * @param cursor Данные полученные из БД черещ DBConnect
     * @return готовый List<ListManager> для загрузки в RecyclerView
     */
    public ArrayList<ListManager> getIndex(Cursor cursor){
        ArrayList<ListManager> mListManagers = new ArrayList();
        if(cursor.moveToFirst()){
            do{
                mListManagers.add(new ListManager(
                        cursor.getString(cursor.getColumnIndex("id")),
                        cursor.getString(cursor.getColumnIndex("surname")),
                        cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("jobplace")),
                        cursor.getString(cursor.getColumnIndex("gender")),
                        cursor.getString(cursor.getColumnIndex("area")),
                        cursor.getString(cursor.getColumnIndex("avto")),
                        cursor.getString(cursor.getColumnIndex("age"))));
            }while (cursor.moveToNext());
        }
        return mListManagers;
    }
}
