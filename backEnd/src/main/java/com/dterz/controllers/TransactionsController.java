package com.dterz.controllers;

import com.dterz.dtos.TransactionDTO;
import com.dterz.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/transactions/")
@CrossOrigin
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    @DeleteMapping("delete/{transactionId}")
    public void delete(@PathVariable("transactionId") long transactionId) {
        transactionsService.deleteTransaction(transactionId);
    }

    @GetMapping("createDraftTransaction/{accountId}")
    public TransactionDTO createDraftTransaction(@PathVariable("accountId") String accountId) {
        return transactionsService.createDraftTransaction(accountId);
    }

    @PutMapping("update")
    public TransactionDTO update(@RequestBody TransactionDTO transaction) {
        return transactionsService.updateTransaction(transaction);
    }

    @GetMapping("getTransactionIdById/{transactionId}")
    public TransactionDTO getTransactionIdById(@PathVariable("transactionId") long transactionId) {
        return transactionsService.getTransactionIdById(transactionId);
    }


    @GetMapping("getAllForUser")
    public ResponseEntity<Map<String, Object>> getAllFiltered(@RequestParam(name = "sort", defaultValue = "date") String sort,
                                                              @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "5") int size,
                                                              @RequestParam(name = "description", defaultValue = "") String description,
                                                              @RequestParam(name = "type", defaultValue = "") String type,
                                                              @RequestParam(name = "userID", defaultValue = "") String userID) {
        PageRequest pageRequest = PageRequest.of(page, size, direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        return transactionsService.getAllForUser(pageRequest, userID, description);
    }

    @GetMapping("getAllForAccount")
    public ResponseEntity<Map<String, Object>> getAllForAccount(@RequestParam(name = "sort", defaultValue = "date") String sort,
                                                                @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "5") int size,
                                                                @RequestParam(name = "accountId", defaultValue = "") String accountId) {
        PageRequest pageRequest = PageRequest.of(page, size, direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        return transactionsService.getAllForAccount(pageRequest, accountId);
    }
}
