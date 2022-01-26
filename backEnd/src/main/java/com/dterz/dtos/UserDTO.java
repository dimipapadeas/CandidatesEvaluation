package com.dterz.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    @JsonProperty("uName")
    private String uName;

    @Getter
    @Setter
    private String salt;

    @Getter
    @Setter
    private String pass;

    @Getter
    @Setter
    @JsonProperty("fName")
    private String fName;

    @Getter
    @Setter
    @JsonProperty("sName")
    private String sName;

    @Getter
    @Setter
    private String comments;

    @Getter
    @Setter
    @JsonProperty("superAdmin")
    private boolean superAdmin;
}
