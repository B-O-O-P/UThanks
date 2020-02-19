package com.uthanks.controller;

import com.uthanks.services.UserService;
import org.springframework.stereotype.Controller;

/**
 * Base class for all pages.
 */
@Controller
public class Page {
    private static final String USER_ID_SESSION_KEY = "userId";

    private UserService userService;
}
