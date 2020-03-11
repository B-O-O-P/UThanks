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

    boolean validateNullValue(Errors errors, Object object, String valueName) {
        if (object == null) {
            errors.rejectValue(valueName, valueName + ".is.null",
                    valueName + " can not be null");
            return false;
        }
        return true;
    }
}
