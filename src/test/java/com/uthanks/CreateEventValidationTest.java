package com.uthanks;

import com.uthanks.domain.Event;
import com.uthanks.form.validators.EventValidator;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CreateEventValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventValidator validator;

    @Test
    public void testValidateGoodEvent() {
        Event event = new Event();
        event.setName("Event");
        Calendar beginDate = new GregorianCalendar(2013, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNullName() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName(null);
        Calendar beginDate = new GregorianCalendar(2013, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("name"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateEmptyName() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("");
        Calendar beginDate = new GregorianCalendar(2013, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("name"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateBlankName() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("     ");
        Calendar beginDate = new GregorianCalendar(2013, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("name"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNullBeginTime() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("Lala");
        event.setEventBeginTime(null);
        Calendar endDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("eventBeginTime"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("eventBeginTime"));
        assertEquals(errors.getFieldError("eventBeginTime").getCode(), "eventBeginTime.is.null");
    }

    @Test
    public void testValidateNullEndTime() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("Lala");
        Calendar beginDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        event.setEventEndTime(null);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("eventEndTime"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("eventEndTime"));
        assertEquals(errors.getFieldError("eventEndTime").getCode(), "eventEndTime.is.null");
    }

    @Test
    public void testValidateEndBeforeBeginDate() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("Lala");
        Calendar beginDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2011, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(10);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("eventEndTime"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("eventEndTime"));
        assertEquals(errors.getFieldError("eventEndTime").getCode(), "end.date.before.begin.date");
    }

    @Test
    public void testValidateNullNeededUsers() {
        int errorsCount = 1;

        Event event = new Event();
        event.setName("Lala");
        Calendar beginDate = new GregorianCalendar(2015, Calendar.FEBRUARY,2);
        event.setEventBeginTime(beginDate);
        Calendar endDate = new GregorianCalendar(2020, Calendar.FEBRUARY,2);
        event.setEventEndTime(endDate);
        event.setNeededUsers(-122);

        Errors errors = new BeanPropertyBindingResult(event, "");
        validator.validate(event, errors);
        assertTrue(errors.hasFieldErrors("neededUsers"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("neededUsers"));
        assertEquals(errors.getFieldError("neededUsers").getCode(), "too.little.needed.users");
    }
}
