package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    @Getter
    @Setter
    private String jwttoken;

    @Getter
    @Setter
    private long userId;

    public JwtResponse() {
        this.jwttoken = null;
        this.userId = 0;
    }

    public JwtResponse(String jwttoken, long userId) {
        this.jwttoken = jwttoken;
        this.userId = userId;
    }

}
