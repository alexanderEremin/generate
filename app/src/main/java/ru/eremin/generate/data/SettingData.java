package ru.eremin.generate.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import ru.eremin.generate.data.managers.ConstantManager;

public class SettingData {
    private SharedPreferences mSh;
    private int mGeneratorTime, mAge_from, mAge_before;
    public SettingData(Context context){
        mSh = PreferenceManager.getDefaultSharedPreferences(context);
        initDefaultData();
    }
    /**
     * Инициализация настроек (Нужно для первого запуска приложения)
     */
    private void initDefaultData(){
        if(mSh.getInt(ConstantManager.generation, 0) == 0){
            mSh.edit().putInt(ConstantManager.generation, 10).apply();
            mGeneratorTime = 10;
        }else
            mGeneratorTime = mSh.getInt(ConstantManager.generation, 10);

        if(mSh.getInt(ConstantManager.age_from, 0) == 0) {
            mSh.edit().putInt(ConstantManager.age_from, 26).apply();
            mAge_from = 26;
        }else
            mAge_from = mSh.getInt(ConstantManager.age_from, 26);

        if(mSh.getInt(ConstantManager.age_before, 0) == 0) {
            mSh.edit().putInt(ConstantManager.age_before, 31).apply();
            mAge_before = 31;
        }else
            mAge_before = mSh.getInt(ConstantManager.age_before, 31);
    }
    /**
     * Получить данные по настройкам
     * @return int значение хранищееся в preference
     */
    public int getmGeneratorTime() {
        return mGeneratorTime;
    }
    public int getmAge_from() {
        return mAge_from;
    }
    public int getmAge_before() {
        return mAge_before;
    }
    /**
     * Установить данные которые указал пользователь
     * TODO Во время записи нужно вернуть остановку потока генерации,
     * иначе из 30 пользователей (10 может быть с возрастом из старых "рамок",
     * а 20 из новых установленных пользователем).
     */
    public void setmGeneratorTime(int generatorTime) {
        mSh.edit().putInt(ConstantManager.generation, generatorTime).apply();
        this.mGeneratorTime = generatorTime;
    }
    public void setmAge_from(int age_from) {
        mSh.edit().putInt(ConstantManager.age_from, age_from).apply();
        this.mAge_from = age_from;
    }
    public void setmAge_before(int age_before) {
        mSh.edit().putInt(ConstantManager.age_before, age_before).apply();
        this.mAge_before = age_before;
    }
}
