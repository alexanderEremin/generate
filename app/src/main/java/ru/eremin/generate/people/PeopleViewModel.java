package ru.eremin.generate.people;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import ru.eremin.generate.ServiceApp;
import ru.eremin.generate.data.managers.ConstantManager;
import ru.eremin.generate.data.managers.ListManager;

public class PeopleViewModel extends AndroidViewModel {
    private Intent intent;
    private Application application;
    private MutableLiveData<Boolean> statusProgress;
    private MutableLiveData<List<ListManager>> listData;
    public PeopleViewModel(@NonNull Application application){
        super(application);
        this.application = application;
        statusProgress = new MutableLiveData<>();
        listData = new MutableLiveData<>();
        intent = new Intent(application, ServiceApp.class);
    }

    MutableLiveData<Boolean> getStatusProgress() {
        return statusProgress;
    }
    MutableLiveData<List<ListManager>> getListData() {
        return listData;
    }

    /**
     * Получить сгенерированные данные
     */
    void getGetData(){
        application.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                listData.setValue(intent.getParcelableArrayListExtra(ConstantManager.serviceDataId));
            }
        }, new IntentFilter(ConstantManager.broadcast));
        application.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (Objects.requireNonNull(intent.getStringExtra(ConstantManager.statusdroadcast))){
                    case "start":
                        statusProgress.setValue(true);
                        break;
                    case "complete":
                        statusProgress.setValue(false);
                        break;
                }
            }
        }, new IntentFilter(ConstantManager.statusdroadcast));
        application.startService(intent);
        statusProgress.setValue(true);
    }

}
