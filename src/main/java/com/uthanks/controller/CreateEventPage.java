package com.uthanks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;


/**
 * Class for CreateEvent page of website.
 */

@Controller
public class CreateEventPage extends Page {
    @GetMapping(path = "/create-event")
    public String index() {
        return "create-event";
    }
}
