package com.uthanks.form.validators;

import org.apache.commons.lang3.StringUtils;
import com.uthanks.form.UserCredentials;
import com.uthanks.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Class for validation of data for registration.
 */
@Component
public class UserCredentialsRegisterValidator implements Validator {
    private final UserService userService;

    public UserCredentialsRegisterValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserCredentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        UserCredentials registerForm = (UserCredentials) target;

        if (StringUtils.isBlank(registerForm.getLogin())) {
            String errorCode = "login.is.empty";
            String errorMessage = "login can not be empty";
            errors.rejectValue("login", errorCode, errorMessage);
        } else if (!userService.isLoginVacant(registerForm.getLogin())) {
            String errorCode = "login.is.in.use";
            String errorMessage = "login is in use";
            errors.rejectValue("login", errorCode, errorMessage);
        }

        if (StringUtils.isBlank(registerForm.getEmail())) {
            String errorCode = "email.is.empty";
            String errorMessage = "email can not be empty";
            errors.rejectValue("email", errorCode, errorMessage);
        }
    }
}