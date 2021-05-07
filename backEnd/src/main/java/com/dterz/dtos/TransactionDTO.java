package com.dterz.dtos;

import com.dterz.model.TransanctionType;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

    private long id;

    private BigDecimal amount;

    private Date date;

    private String description;

    private TransanctionType type;

    private String userName;

    private String accountName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransanctionType getType() {
        return type;
    }

    public void setType(TransanctionType type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
