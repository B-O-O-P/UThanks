package com.uthanks.controller;

import com.uthanks.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Class for profile page of website.
 */
@Controller
public class UserPage extends Page {
    @GetMapping(path = "/user/{id}")
    public String index(@PathVariable("id") String requestId, Model model) {
        try {
            User user = getUserService().findById(Long.parseLong(requestId));
            if (user != null) {
                model.addAttribute("userInfo", user);
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "user";
    }
}
