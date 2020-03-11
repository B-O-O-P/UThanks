package com.uthanks.form.validators;

import com.uthanks.domain.Role.RoleName;
import com.uthanks.form.UserCredentials;
import com.uthanks.services.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;

/**
 * Class for validation of data for registration.
 */
@Component
public class UserCredentialsRegisterValidator extends CredentialsValidator implements Validator {
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

        validateIsBlank(errors, registerForm.getEmail(), "email");

        validateIsBlank(errors, registerForm.getName(), "name");

        validateLogin(registerForm.getLogin(), errors);

        validatePassword(registerForm.getPassword(), errors);

        validateRole(registerForm.getUserType(), errors);
    }

    private boolean validateRole(RoleName role, Errors errors) {
        if (!validateNullValue(errors, role, "userType")) {
            return false;
        }

        if (role != RoleName.VOLUNTEER && role != RoleName.ORGANIZATION) {
            errors.rejectValue("userType", "type.is.empty",
                    "choose register as company or person");
            return false;
        }
        return true;
    }

    private boolean validateLogin(String login, Errors errors) {
        if (!validateIsBlank(errors, login, "login")) {
            return false;
        }

        if (!userService.isLoginVacant(login)) {
            errors.rejectValue("login", "login.is.in.use", "login is in use");
            return false;
        }

        return true;
    }

    private boolean validatePassword(String password, Errors errors) {
        if (StringUtils.isEmpty(password)) {
            errors.rejectValue("password", "password.is.null",
                    "password can not be null");
            return false;
        }
        int passwordLen = password.length();
        if (passwordLen < 4) {
            errors.rejectValue("password", "password.is.too.short",
                    "password is too short");
            return false;
        }
        if (passwordLen > 32) {
            errors.rejectValue("password", "password.is.too.long",
                    "password is too long");
            return false;
        }
        return true;
    }

}