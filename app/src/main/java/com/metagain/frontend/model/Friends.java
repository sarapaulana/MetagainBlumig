package com.metagain.frontend.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Friends {

    private UUID id;

    private Profile friendsProfile;

    private int radius;

    private boolean inRadius;

}
