package com.uthanks.controller;

import com.uthanks.domain.User;
import com.uthanks.services.UserService;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * Base class for all pages.
 */
@Controller
public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";

    private UserService userService;

    void setUser(HttpSession httpSession, User user) {
        //System.out.println(user.getLogin()); // debugInfo
        //System.out.println(user.getEmail()); // debugInfo
        /*if (user != null) {
            httpSession.setAttribute(USER_ID_SESSION_KEY, user.getId());
        } else {
            httpSession.removeAttribute(USER_ID_SESSION_KEY);
        }*/
    }
}