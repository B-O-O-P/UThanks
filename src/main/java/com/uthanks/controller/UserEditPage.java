package com.uthanks.controller;

import com.uthanks.domain.User;
import com.uthanks.form.validators.EditUserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserEditPage extends Page {
    private final EditUserValidator editUserValidator;

    public UserEditPage(EditUserValidator editUserValidator) {
        this.editUserValidator = editUserValidator;
    }

    @InitBinder("userInfo")
    public void initRegisterFormBinder(WebDataBinder binder) {
        binder.addValidators(editUserValidator);
    }

    @GetMapping(path = "/user/{id}/edit")
    public String editGet(@PathVariable("id") String requestId, Model model) {
        try {
            User user = getUserService().findById(Long.parseLong(requestId));
            if (user != null) {
                model.addAttribute("userInfo", user);
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "user-edit";
    }

    @PostMapping(path = "/user/{id}/edit")
    public String editPost(@Valid @ModelAttribute("userInfo") User updatedUser,
                           BindingResult bindingResult,
                           HttpSession httpSession, @PathVariable("id") String postId) {
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }

        try {
            setUser(httpSession, getUserService().saveAdditionalInfo(Long.parseLong(postId), updatedUser.getAge(),
                    updatedUser.getCountry(), updatedUser.getName(), updatedUser.getSkills()));
        } catch (NumberFormatException e) {
            return "user-edit";
        }

        return "redirect:/user/" + postId;
    }
}
