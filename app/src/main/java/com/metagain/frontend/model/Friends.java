package com.metagain.frontend.model;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Friends implements Serializable {

    private UUID id;

    private Profile friendsProfile;

    private int radius;

    private boolean inRadius;

    public UUID getId() {
        return id;
    }

    public Profile getFriendsProfile() {
        return friendsProfile;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isInRadius() {
        return inRadius;
    }
}
