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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

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
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String login = currentDateTime.format(formatter);

        user = new UserCredentials(
                login,
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

    @Test
    public void testValidateGoodUser() {
        validator.validate(user, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNotNullName() {
        user.setName(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotEmptyName() {
        user.setName("    ");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotNullLogin() {
        user.setLogin(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNotEmptyLogin() {
        user.setLogin("   ");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("login").getCode(), "login.is.empty");
    }

    @Test
    public void testValidateNotTooShortPassword() {
        user.setPassword("p12");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.short");
    }

    @Test
    public void testValidateNotTooLongPassword() {
        user.setPassword("1234567890123456789012345678901234567890");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.too.long");
    }

    @Test
    public void testValidateNotNullPassword() {
        user.setPassword(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateNotEmptyPassword() {
        user.setPassword("");
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("password").getCode(), "password.is.null");
    }

    @Test
    public void testValidateNotNullEmail() {
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
    public void testValidateNotNullRole() {
        user.setUserType(null);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("userType").getCode(), "userType.is.null");
    }

    @Test
    public void testValidateNotInvalidRole() {
        user.setUserType(Role.RoleName.ADMIN);
        validator.validate(user, errors);
        assertEquals(errors.getFieldError("userType").getCode(), "type.is.empty");
    }

    @Test
    public void testValidateNotNullRoleAndNullLogin() {
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
