package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Class for companies page of website.
 */
@Controller
public class EventsPage extends Page {
    @GetMapping(path = "/events")
    public String index(Model model) {
        model.addAttribute("events", getEventService().findAll());
        return "events";
    }
}
