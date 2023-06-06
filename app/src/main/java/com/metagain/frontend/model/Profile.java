package com.metagain.frontend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Profile {

    private UUID id;

    private String firstName;

    private String lastName;

    private String username;

}
