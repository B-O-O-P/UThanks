package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for companies page of website.
 */
@Controller
public class NotFoundPage extends Page {
    @GetMapping(path = "/not-found")
    public String index() {
        return "not-found";
    }
}
