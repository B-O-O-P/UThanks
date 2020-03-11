package com.uthanks;

import com.uthanks.domain.Role;
import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsRegisterValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Objects;

import static org.junit.Assert.*;

// Used our database(
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserCredentialsRegisterValidator validator;

    @Test
    public void testValidateGoodUser() {
        UserCredentials user = new UserCredentials(
                "Lll",
                "Lalala",
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNullName() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "Lll",
                null,
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("name"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateEmptyName() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "Lll",
                "    ",
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("name"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("name"));
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNullLogin() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                null,
                "Aaaa",
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
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
        UserCredentials user = new UserCredentials(
                "   ",
                "Aaaa",
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("login"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("login"));
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateTooShortPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "p12",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.short");
    }

    @Test
    public void testValidateTooLongPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "1234567890123456789012345678901234567890",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.long");
    }

    @Test
    public void testValidateNullPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                null,
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateEmptyPassword() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("password"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("password"));
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateNullEmail() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "1234",
                null,
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("email"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("email"));
        assertEquals(errors.getFieldError("email").getCode(), "email.is.empty");
    }

    @Test
    public void testValidateEmptyEmail() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "1234",
                "",
                Role.RoleName.ORGANIZATION);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("email"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("email"));
        assertEquals(errors.getFieldError("email").getCode(), "email.is.empty");
    }

    @Test
    public void testValidateNullRole() {
        int errorsCount = 1;
        UserCredentials user = new UserCredentials(
                "AAAA",
                "Aaaa",
                "1234",
                "a@a.ru",
                null);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("userType"));
        assertEquals(errors.getErrorCount(), errorsCount);
        assertNotNull(errors.getFieldError("userType"));
        assertEquals(errors.getFieldError("userType").getCode(), "type.is.null");
    }

    @Test
    public void testValidateNullRoleAndNullLogin() {
        int errorsCount = 2;
        UserCredentials user = new UserCredentials(
                null,
                "Aaaa",
                "1234",
                "a@a.ru",
                null);
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("userType"));
        assertTrue(errors.hasFieldErrors("login"));
        assertEquals(errors.getErrorCount(), errorsCount);
    }
}
