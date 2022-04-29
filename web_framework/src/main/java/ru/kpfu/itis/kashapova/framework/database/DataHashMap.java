package ru.kpfu.itis.kashapova.framework.database;

import java.util.HashMap;

public class DataHashMap implements DataMap {
    protected HashMap<String, Object> dataHashMap;

    public DataHashMap() {
        this.dataHashMap = new HashMap<>();
    }

    public DataHashMap(HashMap<String, Object> dataHashMap) {
        this.dataHashMap = dataHashMap;
    }

    @Override
    public void addData(String key, Object data) {
        dataHashMap.put(key, data);
    }

    @Override
    public HashMap<String, Object> getAllData() {
        return dataHashMap;
    }

    @Override
    public Object getData(Object key) {
        return dataHashMap.get(key);
    }

    @Override
    public void removeData(Object data) {
        dataHashMap.remove(data);
    }
}
