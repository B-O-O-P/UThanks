package com.uthanks;

import com.uthanks.domain.Role;
import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsRegisterValidator;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

// Used our database(
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserCredentialsRegisterValidator validator;

    private UserCredentials user;
    private Errors errors;

    @Before
    public void init() {
        user = new UserCredentials(
                "Lll",
                "Lalala",
                "password",
                "e@mail.ru",
                Role.RoleName.ORGANIZATION);
        errors = new BeanPropertyBindingResult(user, "");
    }

    @Test
    public void testValidateSupportUser() {
        assertTrue(validator.supports(user.getClass()));
    }

    /* Depends on current DataBase if "Lll" is not used */
    @Test
    public void testValidateGoodUser() {
        validator.validate(user, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNullName() {
        user.setName(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateEmptyName() {
        user.setName("    ");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNullLogin() {
        user.setLogin(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateEmptyLogin() {
        user.setLogin("   ");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateTooShortPassword() {
        user.setPassword("p12");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.short");
    }

    @Test
    public void testValidateTooLongPassword() {
        user.setPassword("1234567890123456789012345678901234567890");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.long");
    }

    @Test
    public void testValidateNullPassword() {
        user.setPassword(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateEmptyPassword() {
        user.setPassword("");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateNullEmail() {
        user.setEmail(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("email").getCode(), "email.is.empty");
    }

    @Test
    public void testValidateEmptyEmail() {
        user.setEmail("");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("email").getCode(), "email.is.empty");
    }

    @Test
    public void testValidateNullRole() {
        user.setUserType(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("userType").getCode(), "userType.is.null");
    }

    @Test
    public void testValidateInvalidRole() {
        user.setUserType(Role.RoleName.ADMIN);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("userType").getCode(), "type.is.empty");
    }

    @Test
    public void testValidateNullRoleAndNullLogin() {
        user.setLogin(null);
        user.setUserType(null);
        validator.validate(user, errors);
        assertTrue(errors.hasFieldErrors("userType"));
        assertTrue(errors.hasFieldErrors("login"));
    }

    @Test
    public void testValidateWithErrors() {
        int errorsCount = 1;
        errors.reject("f.f");
        validator.validate(user, errors);
        assertEquals(errors.getErrorCount(), errorsCount);
    }
}
