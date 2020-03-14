package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for search page of website.
 */
@Controller
public class SearchPage extends Page {
    @GetMapping(path = "/search")
    public String index() {
        return "search";
    }

}