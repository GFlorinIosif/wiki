package com.demo.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserAuthController {

    @GetMapping(value = {"/clear"})
    @ResponseBody
    public String clear(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session;
        session = request.getSession(false);
        SecurityContextHolder.clearContext();
        session = request.getSession(false);
        if(null != session) {
            session.invalidate();
        }
        if( null != request.getCookies()) {
            for(Cookie cookie : request.getCookies()) {
                cookie.setMaxAge(0);
            }
        }
        return "{}";
    }


}
