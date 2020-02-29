package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for companies page of website.
 */
@Controller
public class CompaniesPage extends Page {
    @GetMapping(path = "/companies")
    public String index() {
        return "companies";
    }
}
