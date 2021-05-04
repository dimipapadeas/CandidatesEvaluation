package com.dterz.model;

import javax.persistence.*;

@Entity
@Table(name = "TOKEN")
public class JWTToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String token;

    @Column
    private String userName;

    public JWTToken() {
    }

    public JWTToken(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
