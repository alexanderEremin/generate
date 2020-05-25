package ru.eremin.generate.setting;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import ru.eremin.generate.data.SettingData;
import ru.eremin.generate.data.managers.ConstantManager;

public class SettingViewModel extends AndroidViewModel {
    private SettingData settingData;
    private MutableLiveData<Integer> generation, age_from, age_before;
    public SettingViewModel(@NonNull Application application){
        super(application);
        generation = new MutableLiveData<>();
        age_from = new MutableLiveData<>();
        age_before = new MutableLiveData<>();

        settingData = new SettingData(application);

        generation.setValue(settingData.getmGeneratorTime());
        age_from.setValue(settingData.getmAge_from());
        age_before.setValue(settingData.getmAge_before());
    }

    MutableLiveData<Integer> getGeneration() {
        return generation;
    }
    MutableLiveData<Integer> getAge_from() {
        return age_from;
    }
    MutableLiveData<Integer> getAge_before() {
        return age_before;
    }

    void dataFromEditText(String generator, String age_from, String age_before){
        if(compareData(Integer.parseInt(generator), ConstantManager.generate_min, ConstantManager.generate_max))
            settingData.setmGeneratorTime(Integer.parseInt(generator));
        if(Integer.parseInt(age_from) <= Integer.parseInt(age_before)){
            if(compareData(Integer.parseInt(age_from), ConstantManager.age_min, ConstantManager.age_max))
                settingData.setmAge_from(Integer.parseInt(age_from));
            if(compareData(Integer.parseInt(age_before), ConstantManager.age_min, ConstantManager.age_max))
                settingData.setmAge_before(Integer.parseInt(age_before));
        }
    }

    /**
     * Сравнить полученные данные с допустимым диапазоном
     * @param data Данные для определения валидности
     * @param min Минимальное значение
     * @param max максимально допустимое значение
     * @return boolean true проверка пройдена false не пройдена
     */
    private boolean compareData(int data, int min, int max){
        return data >= min && data <= max;
    }
}