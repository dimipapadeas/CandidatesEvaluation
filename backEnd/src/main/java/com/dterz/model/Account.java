package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "calculated_balance")
    @Getter
    @Setter
    private BigDecimal calculatedBalance;

    @Column(name = "last_transaction")
    @Getter
    @Setter
    private Date lastTransaction;

    @ManyToMany
    @JoinTable(name = "ACCOUNT_USER",
            joinColumns = {@JoinColumn(name = "FK_account")},
            inverseJoinColumns = {@JoinColumn(name = "FK_user")})
    @Getter
    @Setter
    private Set<User> users;

    @OneToMany(mappedBy = "account")
    @Getter
    @Setter
    private List<Transaction> transactions;
}
