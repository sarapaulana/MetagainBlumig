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

    public Friends(UUID id, Profile friendsProfile, int radius, boolean inRadius) {
        this.id = id;
        this.friendsProfile = friendsProfile;
        this.radius = radius;
        this.inRadius = inRadius;
    }

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
