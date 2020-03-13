package com.uthanks.form.validators;

import com.uthanks.domain.Event;
import com.uthanks.services.EventService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;

@Component
public class EventValidator extends CredentialsValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        Event event = (Event) target;

        validateIsBlank(errors, event.getName(), "name");

        validateDates(event.getEventBeginTime(), event.getEventEndTime(), errors);

        validateNeededUsers(event.getNeededUsers(), errors);
    }

    private boolean validateDates(Calendar beginDate, Calendar endDate, Errors errors) {
        if (!validateNullValue(errors, beginDate, "eventBeginTime")) {
            return false;
        }

        if (!validateNullValue(errors, endDate, "eventEndTime")){
            return false;
        }

        if (endDate.before(beginDate)) {
            errors.rejectValue("eventEndTime", "end.date.before.begin.date",
                    "start date must come before end date");
            return false;
        }

        return true;
    }

    private boolean validateNeededUsers(int neededUsers, Errors errors) {
        if (neededUsers < 0) {
            errors.rejectValue("neededUsers", "negate.needed.users",
                    "needed users can not be negate");
            return false;
        }

        return true;
    }
}
