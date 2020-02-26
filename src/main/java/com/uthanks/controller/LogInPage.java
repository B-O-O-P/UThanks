package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for log-in form.
 */
@Controller
public class LogInPage extends Page {
    @GetMapping(path = "/log-in")
    public String index() {
        return "log-in";
    }
}