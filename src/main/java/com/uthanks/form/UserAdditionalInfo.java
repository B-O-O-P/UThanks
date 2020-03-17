package com.uthanks.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAdditionalInfo {
    @NotNull
    @NotEmpty
    private String name;

    private String country;

    private int age;

    private String description;

    private String skills;
}
