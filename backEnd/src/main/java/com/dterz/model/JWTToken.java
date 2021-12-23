package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TOKEN")
public class JWTToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column
    @Getter
    @Setter
    private String token;

    @Column
    @Getter
    @Setter
    private String userName;

    public JWTToken() {
    }

    public JWTToken(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
}
