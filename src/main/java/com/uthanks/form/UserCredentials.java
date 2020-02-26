package com.uthanks.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Class for managing user data.
 */
public class UserCredentials {
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIsUser() {
        return isUser;
    }

    public void setIsUser(int isUser) {
        this.isUser = isUser;
    }

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    private int isUser;
}
