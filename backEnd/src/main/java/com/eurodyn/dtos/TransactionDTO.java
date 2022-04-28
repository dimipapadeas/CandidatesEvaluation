package com.eurodyn.dtos;

import java.math.BigDecimal;
import java.util.Date;

import com.eurodyn.model.TransanctionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionDTO {

    private long id;

    private BigDecimal amount;

    private Date date;

    private String description;

    private TransanctionType type;

    private String userName;

    private String accountName;

}
