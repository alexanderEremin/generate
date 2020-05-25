package ru.eremin.generate.data.generate;

import android.content.Context;

import java.util.List;

import ru.eremin.generate.data.managers.ListManager;

public class Generator {
    private Context context;
    private GenerateUsers generateUsers;
    public Generator(Context context){
        this.context = context;
        generateUsers = new GenerateUsers(context);
    }
    public void startGenerator(){
        generateUsers.execute();
    }
    public void stopGenerator(){
        System.out.println("STOP");
        generateUsers.setGenerate(false);
        generateUsers.cancel(true);
    }
    public List<ListManager> getData(){
        return new GetUsers(context).getData();

    }
}
