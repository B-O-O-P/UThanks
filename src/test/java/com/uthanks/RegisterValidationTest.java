package com.uthanks;

import com.uthanks.controller.RegisterPage;
import com.uthanks.domain.User;
import com.uthanks.form.UserCredentials;
import com.uthanks.form.validators.UserCredentialsRegisterValidator;
import com.uthanks.repository.UserRepository;
import com.uthanks.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
    public void validate_null_name() {
        UserCredentials user = new UserCredentials();
        user.setLogin("Lll");
        user.setPassword("password");
        user.setEmail("e@mail.ru");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assert(errors.hasFieldErrors("name"));
    }

    @Test
    public void validate_empty_name() {
        UserCredentials user = new UserCredentials();
        user.setLogin("Lol");
        user.setPassword("password");
        user.setEmail("e@mail.ru");
        user.setName("     ");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assert(errors.hasFieldErrors("name"));
    }

    @Test
    public void validate_empty_login() {
        UserCredentials user = new UserCredentials();
        user.setLogin("    ");
        user.setPassword("password");
        user.setEmail("e@mail.ru");
        user.setName("name");
        Errors errors = new BeanPropertyBindingResult(user, "");
        validator.validate(user, errors);
        assert(errors.hasFieldErrors("login"));
    }
}
