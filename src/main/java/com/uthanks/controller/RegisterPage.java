package com.uthanks.controller;

import com.uthanks.domain.Role.RoleName;
import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsRegisterValidator;
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
 * Class for registration form.
 */
@Controller
public class RegisterPage extends Page {
    private final UserService userService;
    private final UserCredentialsRegisterValidator userCredentialsRegisterValidator;

    public RegisterPage(UserService userService, UserCredentialsRegisterValidator userCredentialsRegisterValidator) {
        this.userService = userService;
        this.userCredentialsRegisterValidator = userCredentialsRegisterValidator;
    }

    @InitBinder("registerForm")
    public void initRegisterFormBinder(WebDataBinder binder) {
        binder.addValidators(userCredentialsRegisterValidator);
    }

    @GetMapping(path = "/sign-up")
    public String registerGet(Model model) {
        model.addAttribute("registerForm", new UserCredentials());
        model.addAttribute("organizationRole", RoleName.ORGANIZATION);
        model.addAttribute("volunteerRole", RoleName.VOLUNTEER);
        return "sign-up";
    }

    @PostMapping(path = "/sign-up")
    public String registerPost(@Valid @ModelAttribute("registerForm") UserCredentials registerForm,
                               BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "sign-up";
        }

        setUser(httpSession, userService.register(registerForm));

        return "redirect:/";
    }
}