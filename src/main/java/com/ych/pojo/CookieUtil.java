package com.ych.pojo;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieUtil {
    public static Map<String, Cookie> getCookie(Cookie[] cookies){
        Map<String,Cookie> maps = new HashMap<>();
        if (cookies != null){
            for (Cookie coo:cookies){
                maps.put(coo.getName(),coo);
            }
        }
        return maps;
    }
}
