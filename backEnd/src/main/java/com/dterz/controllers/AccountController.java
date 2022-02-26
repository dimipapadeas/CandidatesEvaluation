package com.dterz.controllers;

import com.dterz.dtos.AccountDTO;
import com.dterz.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account/")
@CrossOrigin
public class AccountController {

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("getAccountById/{accountId}")
    public AccountDTO getUserById(@PathVariable("accountId") long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("draftAccount")
    public AccountDTO getUserById() {
        return accountService.draftAccount();
    }

    @GetMapping("getAllFiltered")
    public List<AccountDTO> getUser(@RequestParam(name = "userID", defaultValue = "") String userID) {
        return accountService.getAllFiltered(userID);
    }

    @PutMapping("update")
    public AccountDTO update(@RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountDTO);
    }
}
