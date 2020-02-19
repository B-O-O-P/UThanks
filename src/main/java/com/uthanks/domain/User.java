package com.uthanks.domain;

import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Volunteer entity.
 */
public class User {
    @Id
    private long id;

    @NotNull
    @NotEmpty
    private String login;

    private Date creationTime;

    private String fullName;

    private int countryId;

    private int age;

    private String skills;
}
