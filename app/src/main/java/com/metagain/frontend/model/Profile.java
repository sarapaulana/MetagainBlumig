package com.metagain.frontend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Profile {

    private UUID id;

    private String firstName;

    private String lastName;

    private String username;

    public Profile (String username) {
        this.username = username;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
}
