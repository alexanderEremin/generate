package ru.eremin.generate.data.generate;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.TimerTask;

import ru.eremin.generate.data.SettingData;
import ru.eremin.generate.data.generate.db.DBConnect;

/**
 * Генерация данных и запись в БД
 */
public class GenerateUsers extends AsyncTask<Void, Void, Void> {
    private ArrayList<String> mSurname = new ArrayList<>(10),
            mName = new ArrayList<>(10),
            mJobPlace = new ArrayList<>(10),
            mGender = new ArrayList<>(2),
            mArea = new ArrayList<>(5),
            mAvto = new ArrayList<>(2); // Поля для записи сгенерированных данных
    private int age_from, age_before; // Настройки для генерации
    private Context context;
    private ContentValues contentValues;
    private SettingData settingData;
    public boolean generate = true;

    GenerateUsers(Context context) {
        contentValues = new ContentValues();
        this.context = context;
        settingData = new SettingData(context);
        setmSurname();
        setmName();
        setmJobPlace();
        setmGender();
        setmArea();
        setmAvto();
        setAge_from();
        setAge_before();

    }
    /**
     * Установка значений для генератора
     */
    private void setmSurname() {
        mSurname.add("Александров");
        mSurname.add("Бровкин");
        mSurname.add("Власова");
        mSurname.add("Григорьев");
        mSurname.add("Димитрова");
        mSurname.add("Еремин");
        mSurname.add("Желанов");
        mSurname.add("Зотова");
        mSurname.add("Иванов");
        mSurname.add("Климова");
    }
    private void setmName() {
        mName.add("Евгений");
        mName.add("Александр");
        mName.add("Мария");
        mName.add("Алексей");
        mName.add("Юлия");
        mName.add("Адам");
        mName.add("Артем");
        mName.add("Анастасия");
        mName.add("Иван");
        mName.add("Анна");
    }
    private void setmJobPlace(){
        mJobPlace.add("ООО Магнит");
        mJobPlace.add("ООО Пятерочка");
        mJobPlace.add("ООО Рыбалов");
        mJobPlace.add("ИП Маргарян А.В.");
        mJobPlace.add("ООО Крошка картошка");
        mJobPlace.add("АО Калашников");
        mJobPlace.add("ПАО Газпром");
        mJobPlace.add("АО Роснефть");
        mJobPlace.add("ООО Монетка");
        mJobPlace.add("ЗАО УАЗ");
    }
    private void setmGender(){
        mGender.add("М");
        mGender.add("Ж");
    }
    private void setmArea(){
        mArea.add("Кировский");
        mArea.add("Солнечный");
        mArea.add("Юбилейный");
        mArea.add("Фрунзенский");
        mArea.add("Заводской");
    }
    private void setmAvto(){
        mAvto.add("ДА");
        mAvto.add("НЕТ");
    }
    private void setAge_from() {
        this.age_from = settingData.getmAge_from();
    }
    private void setAge_before() {
        this.age_before = settingData.getmAge_before();
    }
    public void setGenerate(boolean generate) {
        this.generate = generate;
    }

    /**
     * Генерация данных
     */
    @Override
    protected Void doInBackground(Void... voids) {
        DBConnect db = new DBConnect(context);
        while (generate){
            age_from = new SettingData(context).getmAge_from();
            age_before = new SettingData(context).getmAge_before();
            db.deleteData();
            for (int i = 0; i < (int) (Math.random() * (101 - 10)) + 10; i++) {
                contentValues.put("surname", mSurname.get((int) (Math.random() * mSurname.size())));
                contentValues.put("name", mName.get((int) (Math.random() * mName.size())));
                contentValues.put("jobplace", mJobPlace.get((int) (Math.random() * mJobPlace.size())));
                contentValues.put("gender", mGender.get((int) (Math.random() * mGender.size())));
                contentValues.put("area", mArea.get((int) (Math.random() * mArea.size())));
                contentValues.put("avto", mAvto.get((int) (Math.random() * mAvto.size())));
                contentValues.put("age", String.valueOf((int) (Math.random() * ((age_before - age_from) + 1)) + age_from));
                db.insertNewNoteDataInDB(contentValues);
            }
            try {
                Thread.sleep(new SettingData(context).getmGeneratorTime()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        db.closeConnectDB();
        return null;
    }
}
