package com.metagain.frontend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class OwnProfile {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private boolean incognito;

    public OwnProfile(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
