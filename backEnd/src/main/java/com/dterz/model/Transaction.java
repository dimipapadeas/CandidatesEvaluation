package com.dterz.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name = "amount")
    @Getter
    @Setter
    private BigDecimal amount;

    @Column(name = "date")
    @Getter
    @Setter
    private Date date;

    @Column(name = "description")
    @Getter
    @Setter
    private String description;

    @Column(name = "type")
    @Getter
    @Setter
    private TransanctionType type;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    @Getter
    @Setter
    private User user;

    @ManyToOne
    @JoinColumn(name = "account_Id")
    @Getter
    @Setter
    private Account account;

}
