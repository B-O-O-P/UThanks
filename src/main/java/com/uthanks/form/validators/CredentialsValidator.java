package com.uthanks.form.validators;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public class CredentialsValidator {
    boolean validateIsBlank(Errors errors, String value, String valueName) {
        if (StringUtils.isBlank(value)) {
            errors.rejectValue(valueName, valueName + ".is.empty",
                    valueName + " can not be empty");
            return false;
        }
        return true;
    }
}
