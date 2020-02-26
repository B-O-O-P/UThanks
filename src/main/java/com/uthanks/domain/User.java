package com.uthanks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Volunteer entity.
 */
@Data
@Entity
@Table(indexes = @Index(columnList = "creationTime"), uniqueConstraints = @UniqueConstraint(columnNames = "login"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @NotEmpty
    private String login;

    @NotNull
    @NotEmpty
    private String email;

    @CreationTimestamp
    private Date creationTime;

    private String fullName;

    private int countryId;

    private int age;

    @Lob
    private String skills;

    @OneToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_events",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "event_id", referencedColumnName = "id")
    )
    @OrderBy("creationTime DESC")
    private List<Event> events;

    @JsonIgnore
    @OneToMany(mappedBy = "sendUser")
    @OrderBy("creationTime DESC")
    private List<Comment> sentComments;

    @JsonIgnore
    @OneToMany(mappedBy = "receiveUser")
    @OrderBy("creationTime DESC")
    private List<Comment> receivedComments;
}
