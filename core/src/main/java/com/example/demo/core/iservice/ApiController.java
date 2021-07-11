package com.example.demo.core.iservice;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class ApiController {
    @ResponseBody
    public static String json(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message);
        return new Gson().toJson(map);
    }
}
