package com.uthanks.controller;

import com.uthanks.domain.Event;
import com.uthanks.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * Class for event page of website.
 */
@Controller
public class EventPage extends Page {
    @GetMapping(path = "/events/{id}")
    public String getEvent(@PathVariable("id") String requestId, Model model) {
        try {
            Event event = getEventService().findById(Long.parseLong(requestId));
            if (event != null) {
                model.addAttribute("eventInfo", event);
                model.addAttribute("volunteers", event.getVolunteers());
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "event";
    }

    @PostMapping(path = "/events/{id}", params = "action=register")
    public String postRegister(@PathVariable("id") String postId, HttpSession httpSession) {
        try {
            Event event = getEventService().findById(Long.parseLong(postId));
            User user = getUser(httpSession);
            if (event != null && user != null) {
                if (event.getVolunteers().contains(user)) {
                    event.getVolunteers().remove(user);
                } else {
                    event.getVolunteers().add(user);
                }
                getEventService().save(event);
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "redirect:/events/" + postId;
    }
}
