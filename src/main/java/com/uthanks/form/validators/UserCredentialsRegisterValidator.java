package com.uthanks.form.validators;

import com.uthanks.domain.Role.RoleName;
import com.uthanks.form.UserCredentials;
import com.uthanks.services.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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

        validateLogin(registerForm.getLogin(), errors);

        if (registerForm.getUserType() != RoleName.VOLUNTEER
                && registerForm.getUserType() != RoleName.ORGANIZATION) {
            errors.rejectValue("userType", "type.is.empty",
                    "choose register as company or person");
        }
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
}