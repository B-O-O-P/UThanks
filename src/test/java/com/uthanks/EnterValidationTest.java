package com.uthanks;

import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsEnterValidator;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnterValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserCredentialsEnterValidator validator;

    private UserCredentials user;
    private Errors errors;

    @Before
    public void init() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String login = currentDateTime.format(formatter);

        user = new UserCredentials();
        user.setLogin(login);
        user.setPassword("password");
        errors = new BeanPropertyBindingResult(user, "");
    }

    @Test
    public void testValidateSupportUser() {
        assertTrue(validator.supports(user.getClass()));
    }

    @Test
    public void testValidateGoodUser() {
        // TODO user must be in database
    }

    @Test
    public void testValidateNotNullLogin() {
        user.setLogin(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNotEmptyLogin() {
        user.setLogin("");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNotNullPassword() {
        user.setPassword(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }

    @Test
    public void testValidateNotEmptyPassword() {
        user.setPassword("");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }

    @Test
    public void testValidateUserNotFoundInDb() {
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "invalid.login.or.password");
    }

    @Test
    public void testValidateWithErrors() {
        int errorsCount = 1;
        errors.reject("f.f");
        validator.validate(user, errors);
        assertEquals(errors.getErrorCount(), errorsCount);
    }
}
