package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for companies page of website.
 */
@Controller
public class CompaniesPage extends Page {
    @GetMapping(path = "/companies")
    public String index(Model model) {
        model.addAttribute("companies", getUserService().findOrganizations());
        return "companies";
    }
}
