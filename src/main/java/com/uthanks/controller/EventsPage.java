package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for companies page of website.
 */
@Controller
public class EventsPage extends Page {
    @GetMapping(path = "/events")
    public String index() {
        return "events";
    }
}
