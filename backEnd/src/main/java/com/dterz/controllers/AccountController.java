package com.dterz.controllers;

import com.dterz.dtos.AccountDTO;
import com.dterz.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/account/")
@CrossOrigin
@RequiredArgsConstructor
public class AccountController {
	
	private final AccountService accountService;
	
	/**
	 * Gets an Acount by its id
	 *
	 * @param accountId the id of the Account to get
	 * @return AccountDTO
	 */
	@GetMapping("getAccountById/{accountId}")
	public AccountDTO getAccountById(@PathVariable("accountId") long accountId) {
		return accountService.getAccountById(accountId);
	}
	
	/**
	 * Creates and returns a new empty Account Object
	 *
	 * @return AccountDTO
	 */
	@GetMapping("draftAccount")
	public AccountDTO createAccount() {
		return accountService.draftAccount();
	}
	
	/**
	 * Gets all Accounts currently in the System.
	 * The parameter is optional and if provided the accounts get filtered by User
	 *
	 * @param userID the username
	 * @return ResponseEntity<Map < String, Object>>
	 */
	@GetMapping("getAll")
	public ResponseEntity<Map<String, Object>> getByUsername(@RequestParam(name = "userID", defaultValue = "") String userID) {
		PageRequest pageRequest = PageRequest.of(0, 100);
		Map<String, Object> response = accountService.getAll(pageRequest, userID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	/**
	 * Updates the Account with the data from the Front end
	 *
	 * @param accountDTO the updated Account
	 * @return AccountDTO
	 */
	@PutMapping("update")
	public AccountDTO update(@RequestBody AccountDTO accountDTO) {
		return accountService.updateAccount(accountDTO);
	}
}
