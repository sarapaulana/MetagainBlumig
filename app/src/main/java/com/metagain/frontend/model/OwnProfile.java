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

    public String getEmail() {
        return email;
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

    public String getPassword() {
        return password;
    }

    public boolean isIncognito() {
        return incognito;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIncognito(boolean incognito) {
        this.incognito = incognito;
    }
}
