package ru.eremin.generate.interfaces;

import java.util.List;

import ru.eremin.generate.data.managers.ListManager;

public interface InterfaceList {
    interface IClickRecycler{
        void click(List<ListManager> listData);
    }
    interface IGetUsers{
        void getData(List<ListManager> data);
    }
}
