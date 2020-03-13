package com.uthanks.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Event {
    private final static String DATE_PATTERN = "yyyy-MM-dd";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id")
    private User creator;

    @NotNull
    @NotEmpty
    private String name;

    @CreationTimestamp
    private Calendar creationTime;

    @DateTimeFormat(pattern = DATE_PATTERN)
    private Calendar eventBeginTime;

    @DateTimeFormat(pattern = DATE_PATTERN)
    private Calendar eventEndTime;

    private int neededUsers;

    @OneToMany
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<User> volunteers;

    @Lob
    private String description;
}
