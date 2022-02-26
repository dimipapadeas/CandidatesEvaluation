package com.dterz.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

public class AccountDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private BigDecimal calculatedBalance;

    @Getter
    @Setter
    private Date lastTransaction;

    @Getter
    @Setter
    private Set<UserDTO> users;
}
