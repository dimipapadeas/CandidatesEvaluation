package com.dterz.controllers;

import com.dterz.dtos.AccountDTO;
import com.dterz.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("getAll")
    public ResponseEntity<Map<String, Object>> getByUsername(@RequestParam(name = "userID", defaultValue = "") String userID) {
        PageRequest pageRequest = PageRequest.of(0, 100);
        return accountService.getAll(pageRequest, userID);
    }

    @PutMapping("update")
    public AccountDTO update(@RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountDTO);
    }
}
