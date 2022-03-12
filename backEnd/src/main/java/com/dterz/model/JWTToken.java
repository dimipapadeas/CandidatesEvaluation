package com.dterz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TOKEN")
@Getter
@Setter
@NoArgsConstructor
public class JWTToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String token;

    @Column
    private String userName;
    
    public JWTToken(String token, String userName) {
        this.token = token;
        this.userName = userName;
    }
}
