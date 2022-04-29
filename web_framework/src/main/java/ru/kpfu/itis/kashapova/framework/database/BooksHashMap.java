package ru.kpfu.itis.kashapova.framework.database;

import java.util.Map;
import java.util.Set;

public class BooksHashMap {
    private DataHashMap bookHashMap;

    public BooksHashMap() {
        this.bookHashMap = new DataHashMap();
        fullInTheBase();
    }

    public BooksHashMap(DataHashMap bookHashMap) {
        this.bookHashMap = bookHashMap;
    }

    public DataHashMap getBookHashMap() {
        return bookHashMap;
    }

    public void setBookHashMap(DataHashMap bookHashMap) {
        this.bookHashMap = bookHashMap;
    }

    private void fullInTheBase(){
        this.bookHashMap.addData("book1", "https://mir-knig.com/view_525346");
        this.bookHashMap.addData("book2", "https://mir-knig.com/view_450494");
        this.bookHashMap.addData("book3", "https://mir-knig.com/view_525386");
        this.bookHashMap.addData("book4", "https://mir-knig.com/view_106032");
    }

    public void getBook(){
        Set<Map.Entry<String, Object>> set = bookHashMap.dataHashMap.entrySet();
        String key;
        String value;
        for (Map.Entry<String, Object> me : set) {
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
        }
    }
}
