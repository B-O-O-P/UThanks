package com.uthanks.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.CascadeType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    private String name;

    @NotNull
    @NotEmpty
    private String email;

    @CreationTimestamp
    private Date creationTime;

    private String fullName;

    private String country;

    private int age;

    @Lob
    private String skills;

    @ManyToOne
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

    // return true if event was added, else return false
    public boolean addEvent(Event event) {
        if (events == null) {
            events = new ArrayList<>();
        }
        return events.add(event);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof User)) {
            return false;
        }

        User other = (User) obj;

        return this.getId() == other.getId();
    }
}
