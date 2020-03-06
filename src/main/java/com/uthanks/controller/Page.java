package com.uthanks.controller;

import com.uthanks.domain.Role.RoleName;
import com.uthanks.domain.User;
import com.uthanks.services.EventService;
import com.uthanks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;

/**
 * Base class for all pages.
 */
@Controller
public class Page {
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private EventService eventService;

    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private UserService userService;

    @ModelAttribute("organizationRole")
    public RoleName getOrganizationRole() {
        return RoleName.ORGANIZATION;
    }

    @ModelAttribute("volunteerRole")
    public RoleName getVolunteerRole() {
        return RoleName.VOLUNTEER;
    }

    void setUser(HttpSession httpSession, User user) {
        if (user != null) {
            httpSession.setAttribute("user", user);
        } else {
            httpSession.removeAttribute("user");
        }
    }

    User getUser(HttpSession httpSession) {
        return (User) httpSession.getAttribute("user");
    }

    void unsetUser(HttpSession httpSession) {
        httpSession.removeAttribute("user");
    }

    UserService getUserService() {
        return userService;
    }

    EventService getEventService() {
        return eventService;
    }
}