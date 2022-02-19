package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "CLIENT_USER")
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

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new java.util.ArrayList<>();

    @ManyToMany
    @JoinTable(name = "USER_PERMISSION",
            joinColumns = {@JoinColumn(name = "FK_USER")},
            inverseJoinColumns = {@JoinColumn(name = "FK_PERMISSION")})
    @Getter
    @Setter
    private Set<Permission> permissions;

    @Getter
    @Setter
    @ManyToMany
    @JoinTable(name = "ACCOUNT_USER",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"))
    private Set<Account> accounts = new LinkedHashSet<>();


    public User() {
    }
}
