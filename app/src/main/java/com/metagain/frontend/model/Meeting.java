package com.metagain.frontend.model;


import lombok.Data;

import java.util.UUID;

@Data
public class Meeting {

    private UUID id;

    private Profile profile;

    private double[] meetingPoint;

    public UUID getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public double[] getMeetingPoint() {
        return meetingPoint;
    }
}
