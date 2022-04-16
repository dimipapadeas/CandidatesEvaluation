package com.eurodyn.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AccountDTO {

    private long id;

    private String description;

    private BigDecimal calculatedBalance;

    private Date lastTransaction;

    private Set<UserDTO> users;
}
