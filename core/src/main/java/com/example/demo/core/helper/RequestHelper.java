package com.example.demo.core.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class RequestHelper {
    public static String getAccessToken(HttpServletRequest req) {
        final String token1 = req.getHeader("Authorization");
        if (token1 != null && token1.contains("Bearer ")) {
            return token1.replace("Bearer", "").trim();
        }
        final String token2 = req.getParameter("access_token");
        if (token2 != null && token2.length() > 5) {
            return token2;
        }
        final String token3 = getCookieValue(req, "access_token");
        if (token3 != null && token3.length() > 5) {
            return token3;
        }
        return "";
    }

    public static String getCookieValue(HttpServletRequest req, String cookieName) {
        for (Cookie cookie : req.getCookies() != null ? req.getCookies() : new Cookie[]{}) {
            if (cookie.getName().equalsIgnoreCase(cookieName)) {
                return cookie.getValue();
            }
        }
        return "";
    }
}
