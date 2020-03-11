package com.uthanks;

import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsEnterValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

// Used our database(
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EnterValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserCredentialsEnterValidator validator;

    @Test
    public void testValidateGoodUser() {
        // TODO user must be in database
    }

    @Test
    public void testValidateNullLogin() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials();
        user.setLogin(null);
        user.setPassword("password");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("login"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("login"));
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateEmptyLogin() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials();
        user.setLogin("");
        user.setPassword("password");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("login"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("login"));
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNullPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials();
        user.setLogin("Aaaaa");
        user.setPassword(null);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }

    @Test
    public void testValidateEmptyPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials();
        user.setLogin("Aaaaa");
        user.setPassword("");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }
}
