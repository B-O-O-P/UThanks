package com.uthanks;

import com.uthanks.form.UserAdditionalInfo;
import com.uthanks.form.validators.UserAdditionalInfoValidator;
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserEditValidationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserAdditionalInfoValidator validator;

    private UserAdditionalInfo additionalInfo;
    private Errors errors;

    @Before
    public void init() {
        additionalInfo = new UserAdditionalInfo();
        additionalInfo.setName("Name");
        errors = new BeanPropertyBindingResult(additionalInfo, "");
    }

    @Test
    public void testValidateGoodUser() {
        validator.validate(additionalInfo, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testValidateNotNullName() {
        additionalInfo.setName(null);
        validator.validate(additionalInfo, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotEmptyName() {
        additionalInfo.setName("");
        validator.validate(additionalInfo, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateNotBlankName() {
        additionalInfo.setName("    ");
        validator.validate(additionalInfo, errors);
        assertEquals(errors.getFieldError("name").getCode(), "name.is.empty");
    }

    @Test
    public void testValidateWithErrors() {
        int errorsCount = 1;
        errors.reject("f.f");
        validator.validate(additionalInfo, errors);
        assertEquals(errors.getErrorCount(), errorsCount);
    }

    @Test
    public void testValidateSupportAdditionalInfo() {
        assertTrue(validator.supports(additionalInfo.getClass()));
    }
}
