package com.eurodyn.dtos;

import com.eurodyn.model.TransanctionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

public class TransactionDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private BigDecimal amount;

    @Getter
    @Setter
    private Date date;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private TransanctionType type;

    @Getter
    @Setter
    private String userName;

    @Getter
    @Setter
    private String accountName;

}
