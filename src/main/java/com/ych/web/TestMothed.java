package com.ych.web;

import com.ych.pojo.CookieUtil;
import com.ych.pojo.User;
import com.ych.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class TestMothed implements HandlerInterceptor {
    @Autowired
    IUserService service;
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object o) throws Exception {
        Cookie[] cookies = req.getCookies();
        Map<String,Cookie> maps = CookieUtil.getCookie(cookies);
        Cookie c = maps.get("username");
        if (c != null){
            String username = c.getValue();
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            return true;
        }else {
            resp.sendRedirect("login.do");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
