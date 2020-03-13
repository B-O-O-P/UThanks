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

    private UserCredentials user;
    private Errors errors;

    @Before
    public void init() {
        user = new UserCredentials();
        user.setLogin("Lalala");
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
    public void testValidateNullLogin() {
        user.setLogin(null);
        validator.validate(user, errors);
        assertNotNull(errors.getFieldError("login"));
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateEmptyLogin() {
        user.setLogin("");
        validator.validate(user, errors);
        assertNotNull(errors.getFieldError("login"));
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNullPassword() {
        user.setPassword(null);
        validator.validate(user, errors);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }

    @Test
    public void testValidateEmptyPassword() {
        user.setPassword("");
        validator.validate(user, errors);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.empty");
    }

    /* Depends on current DataBase if "Lalala" is not used */
    @Test
    public void testValidateUserNotFoundInDb() {
        validator.validate(user, errors);
        assertNotNull(errors.getFieldError("password"));
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
