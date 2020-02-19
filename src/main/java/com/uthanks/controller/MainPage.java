package com.uthanks.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

/**
 * Class for main page of website.
 */
@Controller
public class MainPage extends Page {
    public String index() {
        return "MainPage";
    }

    public String index(HttpSession httpSession) {
        return "redirect:/";
    }
}
