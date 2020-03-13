package com.uthanks.form;

import com.uthanks.domain.Role.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class for managing user data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
    @NotNull
    @NotEmpty
    private String login;

    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 32,
    message = "password must be between {min} and {max} characters long")
    private String password;

    @Email
    private String email;

    private RoleName userType;
}
