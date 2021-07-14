package com.example.demo.core.iservice;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ApiController {
    public static Map json(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message);
        return map;
    }

    public static String jsonString(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message);
        return new Gson().toJson(map);
    }
}
