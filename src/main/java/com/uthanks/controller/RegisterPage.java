package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for registration form.
 */
@Controller
public class RegisterPage extends Page {
    @GetMapping(path = "/sign-up")
    public String index() {
        return "sign-up";
    }
}