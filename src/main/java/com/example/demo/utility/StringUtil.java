package com.example.demo.utility;


import com.google.gson.Gson;

public class StringUtil {
    public static String getObjectToJSON(Object object) {
        try {
            return new Gson().toJson(object);
        }catch (Exception e) {
        }
        return null;
    }
}
