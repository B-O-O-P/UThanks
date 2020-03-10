package com.uthanks.form.validators;

import com.uthanks.domain.Event;
import com.uthanks.services.EventService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EventValidator extends CredentialsValidator implements Validator {
    private final EventService eventService;

    public EventValidator(EventService eventService) {
        this.eventService = eventService;
    }


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

        if (event.getEventEndTime().before(event.getEventBeginTime())) {
            errors.rejectValue("eventEndTime", "end.date.before.begin.date",
                    "finish date does not be before start date");
        }

        if (event.getNeededUsers() <= 0) {
            errors.rejectValue("neededUsers", "too.little.needed.users",
                    "too little needed volunteers");
        }
     }
}
