package com.dterz.dtos;

import java.math.BigDecimal;
import java.util.Date;

public class AccountDTO {

    private long id;

    private String description;

    private BigDecimal calculatedBalance;

    private Date lastTransaction;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCalculatedBalance() {
        return calculatedBalance;
    }

    public void setCalculatedBalance(BigDecimal calculatedBalance) {
        this.calculatedBalance = calculatedBalance;
    }

    public Date getLastTransaction() {
        return lastTransaction;
    }

    public void setLastTransaction(Date lastTransaction) {
        this.lastTransaction = lastTransaction;
    }

}
