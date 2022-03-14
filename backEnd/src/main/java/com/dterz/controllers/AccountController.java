package com.dterz.controllers;

import com.dterz.dtos.AccountDTO;
import com.dterz.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/account/")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("getAccountById/{accountId}")
    public AccountDTO getUserById(@PathVariable("accountId") long accountId) {
        return accountService.getAccountById(accountId);
    }

    @GetMapping("draftAccount")
    public AccountDTO getUserById() {
        return accountService.draftAccount();
    }

    @GetMapping("getByUsername")
    public List<AccountDTO> getByUsername(@RequestParam(name = "userID", defaultValue = "") String userID) {
        return accountService.getByUsername(userID);
    }

    @PutMapping("update")
    public AccountDTO update(@RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountDTO);
    }
}
