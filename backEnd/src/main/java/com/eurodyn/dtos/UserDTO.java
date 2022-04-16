package com.eurodyn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    
    private long id;
    
    private String userName;
    
    private String pass;
    
    private String fistName;
    
    private String surName;
    
    private String comments;
    
    @JsonProperty("superAdmin")
    private boolean superAdmin;
}
