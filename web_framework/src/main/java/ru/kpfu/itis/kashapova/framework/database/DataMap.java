package ru.kpfu.itis.kashapova.framework.database;

import java.util.HashMap;

public interface DataMap {
    void addData(String key, Object data);
    HashMap<String, Object> getAllData();
    Object getData(Object key);
    void removeData(Object data);
}
