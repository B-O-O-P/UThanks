package com.uthanks.controller;

import com.uthanks.domain.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Class for event page of website.
 */
@Controller
public class EventPage extends Page {
    @GetMapping(path = "/events/{id}")
    public String index(@PathVariable("id") String requestId, Model model) {
        try {
            Event event = getEventService().findById(Long.parseLong(requestId));
            if (event != null) {
                model.addAttribute("eventInfo", event);
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "event";
    }
}
