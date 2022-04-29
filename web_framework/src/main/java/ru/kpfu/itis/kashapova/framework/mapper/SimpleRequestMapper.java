package ru.kpfu.itis.kashapova.framework.mapper;

import java.util.HashMap;
import java.util.Map;

public class SimpleRequestMapper implements RequestMapper {
    private Map<String, String> routes = new HashMap<>();

    @Override
    public void addRoute(String path, String controller) {
        routes.put(path, controller);
    }

    @Override
    public String getRoute(String path) {
        return routes.get(path);
    }

    @Override
    public boolean hasRoute(String path) {
        return routes.containsKey(path);
    }
}
