package com.metagain.frontend.model;

import com.metagain.frontend.model.types.RequestType;

import lombok.Data;

import java.util.UUID;

@Data
public class Request {

    private UUID id;

    private Profile profile;

    private RequestType requestType;

    private int radius;

    public Request(Profile profile, RequestType requestType) {
        this.profile = profile;
        this.requestType = requestType;
    }

    public Request(Profile profile, RequestType requestType, int radius) {
        this.profile = profile;
        this.requestType = requestType;
        this.radius = radius;
    }

    public UUID getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
