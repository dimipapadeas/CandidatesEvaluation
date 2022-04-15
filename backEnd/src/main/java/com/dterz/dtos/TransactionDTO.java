package com.dterz.dtos;

import com.dterz.model.TransanctionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {

    private long id;

    private BigDecimal amount;

    private Date date;

    private String description;

    private TransanctionType type;

    private String userName;

    private String accountName;

}
