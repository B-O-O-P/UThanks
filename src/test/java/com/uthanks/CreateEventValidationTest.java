package com.uthanks;

import com.uthanks.domain.Event;
import com.uthanks.form.validators.EventValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateEventValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventValidator validator;

    private Event event;
    private Errors errors;

    @Before
    public void init() {
        event = new Event();
        event.setName("Event");
        Calendar beginDate = new GregorianCalendar(2013, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);
        errors = new BeanPropertyBindingResult(event, "");
    }

    @Test
    public void testValidateSupportEvent() {
        assertTrue(validator.supports(event.getClass()));
    }

    @Test
    public void testValidateGoodEvent() {
        validator.validate(event, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNotNullName() {
        event.setName(null);
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotEmptyName() {
        event.setName("");
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotBlankName() {
        event.setName("     ");
        validator.validate(event, errors);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotNullBeginTime() {
        event.setEventBeginTime(null);
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("eventBeginTime").getCode(), "eventBeginTime.is.null");
    }

    @Test
    public void testValidateNotNullEndTime() {
        event.setEventEndTime(null);
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("eventEndTime").getCode(), "eventEndTime.is.null");
    }

    @Test
    public void testValidateEndBeforeBeginDate() {
        Calendar beginDate = new GregorianCalendar(2016, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("eventEndTime").getCode(), "end.date.before.begin.date");
    }

    @Test
    public void testValidateNotNullNeededUsers() {
        event.setNeededUsers(-122);
        validator.validate(event, errors);
        assertEquals(errors.getFieldError("neededUsers").getCode(), "negate.needed.users");
    }

    @Test
    public void testValidateWithErrors() {
        int errorsCount = 1;
        errors.reject("f.f");
        validator.validate(event, errors);
        assertEquals(errors.getErrorCount(), errorsCount);
    }
}
