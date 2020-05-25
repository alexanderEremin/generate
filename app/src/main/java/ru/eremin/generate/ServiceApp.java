package ru.eremin.generate;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcelable;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

import ru.eremin.generate.data.generate.Generator;
import ru.eremin.generate.data.managers.ConstantManager;
import ru.eremin.generate.data.managers.ListManager;

public class ServiceApp extends Service {
    private Thread thread;
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intentdatastatus = new Intent(ConstantManager.statusdroadcast);
        intentdatastatus.putExtra(ConstantManager.statusdroadcast, "start");
        sendBroadcast(intentdatastatus);
        thread = new Thread(() -> {
            try {
                Thread.sleep(ConstantManager.sleep_mills);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resultData(new Generator(getApplicationContext()).getData());
        });
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean stopService(Intent name) {
        thread.interrupt();
        stopForeground(true);
        stopSelf();
        return super.stopService(name);
    }
    public void resultData(List<ListManager> data){
        Intent intentdata = new Intent(ConstantManager.broadcast);
        intentdata.putParcelableArrayListExtra(ConstantManager.serviceDataId, (ArrayList<? extends Parcelable>) data);
        sendBroadcast(intentdata);

        Intent intentdatastatus = new Intent(ConstantManager.statusdroadcast);
        intentdatastatus.putExtra(ConstantManager.statusdroadcast, "complete");
        sendBroadcast(intentdatastatus);
    }
}
