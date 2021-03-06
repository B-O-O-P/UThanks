package com.uthanks.controller;

import com.uthanks.domain.User;
import com.uthanks.form.UserAdditionalInfo;
import com.uthanks.form.validators.UserAdditionalInfoValidator;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserEditPage extends Page {
    private final UserAdditionalInfoValidator userAdditionalInfoValidator;

    public UserEditPage(UserAdditionalInfoValidator userAdditionalInfoValidator) {
        this.userAdditionalInfoValidator = userAdditionalInfoValidator;
    }

    @InitBinder("userInfo")
    public void initRegisterFormBinder(WebDataBinder binder) {
        binder.addValidators(userAdditionalInfoValidator);
    }

    @GetMapping(path = "/users/{id}/edit")
    public String editGet(@PathVariable("id") String requestId, Model model, HttpSession httpSession) {
        try {
            User sessionUser = getUser(httpSession);
            long requestNumberId = Long.parseLong(requestId);
            if (sessionUser == null || requestNumberId != sessionUser.getId()) {
                return "redirect:/not-found";
            }
            User user = getUserService().findById(Long.parseLong(requestId));
            if (user != null) {
                model.addAttribute("userInfo",
                        new UserAdditionalInfo(user.getName(), user.getCountry(), user.getAge(),
                                user.getDescription(), user.getSkills()));
            }
        } catch (NumberFormatException e) {
            return "redirect:/not-found";
        }
        return "user-edit";
    }

    @PostMapping(path = "/users/{id}/edit")
    public String editPost(@Valid @ModelAttribute("userInfo") UserAdditionalInfo additionalInfo,
                           BindingResult bindingResult,
                           HttpSession httpSession, @PathVariable("id") String postId) {
        if (bindingResult.hasErrors()) {
            return "user-edit";
        }

        try {
            setUser(httpSession, getUserService().saveAdditionalInfo(Long.parseLong(postId), additionalInfo.getAge(),
                    additionalInfo.getCountry(), additionalInfo.getName(), additionalInfo.getDescription(), additionalInfo.getSkills()));
        } catch (NumberFormatException e) {
            return "user-edit";
        }

        return "redirect:/users/" + postId;
    }
}
