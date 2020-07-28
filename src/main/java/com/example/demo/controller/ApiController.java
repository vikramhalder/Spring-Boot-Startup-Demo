package com.example.demo.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

public class ApiController {
    @ResponseBody
    protected String returnJson(boolean status, Object data, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("status", status);
        map.put("data", data);
        map.put("message", message);
        return new Gson().toJson(map);
    }
}
