package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "u_Name")
    @Getter
    @Setter
    private String uName;

    @Column(name = "salt")
    @Getter
    @Setter
    private String salt;

    @Column(name = "pass")
    @Getter
    @Setter
    private String pass;

    @Column(name = "f_Name")
    @Getter
    @Setter
    private String fName;

    @Column(name = "s_Name")
    @Getter
    @Setter
    private String sName;

    @Column(name = "comments")
    @Getter
    @Setter
    private String comments;

    @Column(name = "super_admin")
    @Getter
    @Setter
    private boolean superAdmin;

    @OneToMany(mappedBy = "user")
    @Getter
    @Setter
    private List<Transaction> transactions;

    @ManyToMany
    @JoinTable(name = "USER_PERMISSION",
            joinColumns = {@JoinColumn(name = "FK_USER")},
            inverseJoinColumns = {@JoinColumn(name = "FK_PERMISSION")})
    @Getter
    @Setter
    private Set<Permission> permissions;


    public User() {
    }
}
