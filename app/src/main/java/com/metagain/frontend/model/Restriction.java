package com.metagain.frontend.model;

import com.metagain.frontend.model.types.RestrictionType;

import lombok.Data;

import java.util.UUID;

@Data
public class Restriction {

    private UUID id;

    private String fromUsername;

    private String toUsername;

    private RestrictionType restrictionType;
}
