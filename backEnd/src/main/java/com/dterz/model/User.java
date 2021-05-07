package com.dterz.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uName")
    private String uName;

    @Column(name = "salt")
    private String salt;

    @Column(name = "pass")
    private String pass;

    @Column(name = "fName")
    private String fName;

    @Column(name = "sName")
    private String sName;

    @Column(name = "comments")
    private String comments;

    @Column(name = "superAdmin")
    private boolean superAdmin;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transactions;

    @ManyToMany
    @JoinTable(name = "USER_PERMISSION",
            joinColumns = {@JoinColumn(name = "FK_USER")},
            inverseJoinColumns = {@JoinColumn(name = "FK_PERMISSION")})
    private Set<Permission> permissions;


    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }
}
