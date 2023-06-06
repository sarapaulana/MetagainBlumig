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
}
