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
        if (user != null) {
            httpSession.setAttribute("user", user);
            httpSession.setAttribute("IS_USER", true);
        } else {
            httpSession.removeAttribute("user");
            httpSession.removeAttribute("IS_USER");
        }
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        httpSession.removeAttribute("IS_USER");
    }
}