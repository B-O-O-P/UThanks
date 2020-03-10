package com.uthanks.controller;

import com.uthanks.domain.Event;
import com.uthanks.domain.Role.RoleName;
import com.uthanks.domain.User;
import com.uthanks.form.validators.EventValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


/**
 * Class for CreateEvent page of website.
 */

@Controller
public class CreateEventPage extends Page {
    private final EventValidator eventValidator;

    public CreateEventPage(EventValidator eventValidator) {
        this.eventValidator = eventValidator;
    }

    @InitBinder("event")
    public void initRegisterFormBinder(WebDataBinder binder) {
        binder.addValidators(eventValidator);
    }

    @GetMapping(path = "/create-event")
    public String createEventGet(Model model, HttpSession httpSession) {
        User user = getUser(httpSession);
        if (user == null || (user.getRole().getName() != RoleName.ORGANIZATION)) {
            return "redirect:/";
        }
        model.addAttribute("event", new Event());
        return "create-event";
    }

    @PostMapping(path = "/create-event")
    public String createEventPost(@Valid @ModelAttribute("event") Event event,
                               BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "create-event";
        }

        getEventService().save(event, getUser(httpSession));

        return "redirect:/";
    }
}
