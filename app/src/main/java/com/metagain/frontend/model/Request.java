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

}
