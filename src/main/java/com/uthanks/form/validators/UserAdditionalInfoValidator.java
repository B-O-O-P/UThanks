package com.uthanks.form.validators;

import com.uthanks.form.UserAdditionalInfo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserAdditionalInfoValidator extends CredentialsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserAdditionalInfo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        UserAdditionalInfo additionalInfo = (UserAdditionalInfo) target;

        validateIsBlank(errors, additionalInfo.getName(), "name");
    }
}
