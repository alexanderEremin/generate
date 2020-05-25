package ru.eremin.generate.user;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class UserViewModel extends AndroidViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<String> jobplace;
    private MutableLiveData<String> gender;
    private MutableLiveData<String> area;
    private MutableLiveData<String> avto;
    private MutableLiveData<String> age;

    public UserViewModel(@NonNull Application application) {
        super(application);
        name = new MutableLiveData<>();
        jobplace = new MutableLiveData<>();
        gender = new MutableLiveData<>();
        area = new MutableLiveData<>();
        avto = new MutableLiveData<>();
        age = new MutableLiveData<>();
    }

    /**
     * Получение данных о пользователе
     */
    void setDataFromBundle(Bundle argument){
        UserFragmentArgs data = UserFragmentArgs.fromBundle(argument);
        name.setValue(data.getName()+" "+data.getSurname());
        jobplace.setValue(data.getJobplace());
        gender.setValue(data.getGender());
        area.setValue(data.getArea());
        avto.setValue(data.getAvto());
        age.setValue(data.getAge());
    }

    public MutableLiveData<String> getName() {
        return name;
    }
    public MutableLiveData<String> getJobplace() {
        return jobplace;
    }
    public MutableLiveData<String> getGender() {
        return gender;
    }
    public MutableLiveData<String> getArea() {
        return area;
    }
    public MutableLiveData<String> getAvto() {
        return avto;
    }
    public MutableLiveData<String> getAge() {
        return age;
    }
}
