package com.example.demo.Util;


import com.google.gson.Gson;

public class StringUtil {
    public static String getObjetToJSON(Object object) {
        try {
            return new Gson().toJson(object);
        }catch (Exception e) {
        }
        return null;
    }
}
