package com.metagain.frontend.model;


import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class Meeting implements Serializable {

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

    public void setMeetingPoint(double[] meetingPoint) {
        this.meetingPoint = meetingPoint;
    }
}
