package com.uthanks.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long compId;

    @CreationTimestamp
    private Date creationTime;

    @DateTimeFormat
    private Date eventBeginTime;

    @DateTimeFormat
    private Date eventEndTime;

    private int neededUsers;

    private String skills;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCompId() {
        return compId;
    }

    public void setCompId(long compId) {
        this.compId = compId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getEventBeginTime() {
        return eventBeginTime;
    }

    public void setEventBeginTime(Date eventBeginTime) {
        this.eventBeginTime = eventBeginTime;
    }

    public Date getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Date eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public int getNeededUsers() {
        return neededUsers;
    }

    public void setNeededUsers(int neededUsers) {
        this.neededUsers = neededUsers;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
