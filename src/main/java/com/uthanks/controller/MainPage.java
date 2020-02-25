package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for main page of website.
 */
@Controller
public class MainPage extends Page {
    @GetMapping(path = "")
    public String index() {
        return "common";
    }

    public String index(HttpSession httpSession) {
        return "redirect:/";
    }
}
