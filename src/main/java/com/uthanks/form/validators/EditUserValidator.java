package com.uthanks.form.validators;

import com.uthanks.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EditUserValidator extends CredentialsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        User user = (User) target;

        validateIsBlank(errors, user.getName(), "name");
    }
}
