package ru.eremin.generate.data.generate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.lifecycle.LifecycleObserver;

public class DBConnect implements LifecycleObserver {
    private SQLiteDatabase mSQLiteDatabase;

    private DBHelper db;

    public DBConnect(Context context){
        db = new DBHelper(context);
    }
    /**
     * Получаем все записи
     * @return вернет все записи
     */
    public Cursor connectToDB(){
        mSQLiteDatabase = db.getReadableDatabase();
        return mSQLiteDatabase.query("Data", null, null, null, null, null, null);
    }
    /**
     * Подсчитать сколько строк в БД
     * @return int - количество записей в БД
     */
    public int countToDB(){
        return connectToDB().getCount();
    }
    /**
     * Получить запись по status
     * @param status  статус по которому ведется поиск "open" or "close"
     * @return  вернет список записей
     */
    public Cursor connectToDB(String selection, String status) {
        mSQLiteDatabase = db.getReadableDatabase();
        return mSQLiteDatabase.query("Data", null, selection, new String[]{status}, null, null, null);
    }
    /**
     * Удалить запись
     * @param id идентификатор записи
     */
    public void deleteDataFromDB(Integer id){
        String idS = String.valueOf(id);
        mSQLiteDatabase = db.getWritableDatabase();
        mSQLiteDatabase.delete("Data", "id = ?", new String[]{idS});
    }
    /**
     * Удалить запись
     */
    public void deleteData(){
        mSQLiteDatabase = db.getWritableDatabase();
        mSQLiteDatabase.delete("Data", null, null);
    }
    /**
     * Вставить новую запись
     * @param mContentValues  данные для записи в БД
     */
    public void insertNewNoteDataInDB(ContentValues mContentValues){
        mSQLiteDatabase = db.getWritableDatabase();
        mSQLiteDatabase.insert("Data", null, mContentValues);
    }
    /**
     * Изменить запись
     */
    public void refactorNote(String id, ContentValues contentValues){
        mSQLiteDatabase = db.getWritableDatabase();
        mSQLiteDatabase.update("Data", contentValues, "id = ?", new String[]{id});
    }
    /**
     * Закрыть соединение
     */
    public void closeConnectDB(){
        db.close();
    }
}
