package com.dterz.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private long userId;

    public JwtResponse() {
        this.jwttoken = null;
        this.userId = 0;
    }

    public JwtResponse(String jwttoken, long userId) {
        this.jwttoken = jwttoken;
        this.userId = userId;
    }

    public String getToken() {
        return this.jwttoken;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
