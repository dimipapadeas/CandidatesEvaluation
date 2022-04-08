package com.eurodyn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String salt;

    @Getter
    @Setter
    private String pass;

    @Getter
    @Setter
    private String fistName;

    @Getter
    @Setter
    private String surName;

    @Getter
    @Setter
    private String comments;

    @Getter
    @Setter
    @JsonProperty("superAdmin")
    private boolean superAdmin;
}
