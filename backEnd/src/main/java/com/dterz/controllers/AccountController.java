package com.dterz.controllers;

import com.dterz.dtos.AccountDTO;
import com.dterz.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account/")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("getAccountById/{accountId}")
    public AccountDTO getUserById(@PathVariable("accountId") long accountId) {
        return accountService.getAccountById(accountId);
    }


    @GetMapping("getAllFiltered")
    public List<AccountDTO> getUser(@RequestParam(name = "userID", defaultValue = "") String userID) {
        return accountService.getAllFiltered(userID);
    }
}
