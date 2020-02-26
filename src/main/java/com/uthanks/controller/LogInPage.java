package com.uthanks.controller;

import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsEnterValidator;
import com.uthanks.services.UserService;
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
 * Class for log-in form.
 */
@Controller
public class LogInPage extends Page {
    //private final UserService userService;
    private final UserCredentialsEnterValidator userCredentialsEnterValidator;

    public LogInPage(UserService userService, UserCredentialsEnterValidator userCredentialsEnterValidator) {
        //this.userService = userService;
        this.userCredentialsEnterValidator = userCredentialsEnterValidator;
    }

    @InitBinder("enterForm")
    public void initRegisterFormBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsEnterValidator);
    }

    @GetMapping(path = "/log-in")
    public String loginGet(Model model) {
        model.addAttribute("enterForm", new UserCredentials());
        return "log-in";
    }

    @PostMapping(path = "/log-in")
    public String loginPost(@Valid @ModelAttribute("enterForm") UserCredentials enterForm,
                            BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "log-in";
        }

        System.out.println(enterForm.getLogin());
        System.out.println(enterForm.getPassword());
        setUser(httpSession, getUserService().findByLoginAndPassword(enterForm.getLogin(), enterForm.getPassword()));

        return "redirect:/";
    }
}