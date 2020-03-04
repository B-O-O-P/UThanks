package com.uthanks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Name name;

    public enum Name {
        VOLUNTEER,
        ORGANIZATION,
        ADMIN
    }
}
