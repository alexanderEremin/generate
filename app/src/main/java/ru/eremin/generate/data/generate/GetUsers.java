package ru.eremin.generate.data.generate;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ru.eremin.generate.data.generate.db.CursorParser;
import ru.eremin.generate.data.generate.db.DBConnect;
import ru.eremin.generate.data.managers.ListManager;

public class GetUsers{
    private Context context;
    GetUsers(Context context){
        this.context = context;
    }
    public List<ListManager> getData() {
        DBConnect dbConnect = new DBConnect(context);
        ArrayList<ListManager> result = new CursorParser().getIndex(dbConnect.connectToDB());
        dbConnect.closeConnectDB();
        return result;
    }
}
