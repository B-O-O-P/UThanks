package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for profile page of website.
 */
@Controller
public class UserPage extends Page {
    @GetMapping(path = "/user")
    public String index() {
        return "user";
    }
}
