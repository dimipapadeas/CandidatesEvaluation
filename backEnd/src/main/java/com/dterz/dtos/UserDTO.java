package com.dterz.dtos;

import lombok.Getter;
import lombok.Setter;

public class UserDTO {

    @Getter
    @Setter
    private long id;

    @Getter
    @Setter
    private String uName;

    @Getter
    @Setter
    private String salt;

    @Getter
    @Setter
    private String pass;

    @Getter
    @Setter
    private String fName;

    @Getter
    @Setter
    private String sName;

    @Getter
    @Setter
    private String comments;

    @Getter
    @Setter
    private boolean superAdmin;
}
