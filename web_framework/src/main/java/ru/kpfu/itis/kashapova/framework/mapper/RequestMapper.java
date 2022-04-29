package ru.kpfu.itis.kashapova.framework.mapper;

import java.util.Map;

public interface RequestMapper {
    void addRoute(String path, String controller);
    String getRoute(String path);
    boolean hasRoute(String path);
}
