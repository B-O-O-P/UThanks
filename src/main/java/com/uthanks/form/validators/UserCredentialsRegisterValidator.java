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
        if (!errors.hasErrors()) {
            UserCredentials registerForm = (UserCredentials) target;

            if (StringUtils.isAnyBlank(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is.empty", "login can not be empty");
            } else if (!userService.isLoginVacant(registerForm.getLogin())) {
                errors.rejectValue("login", "login.is.in.use", "login is in use");
            }

            if (StringUtils.isAnyBlank(registerForm.getEmail())) {
                errors.rejectValue("email", "email.is.empty", "email can not be empty");
            }

            /*if (registerForm.getEmail() == null || registerForm.getEmail().isEmpty()) {
                errors.rejectValue("email", "email.is.empty", "email can not be empty");
            }*/

        }
    }
}