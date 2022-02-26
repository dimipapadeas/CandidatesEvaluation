package com.dterz.controllers;

import com.dterz.dtos.TransactionDTO;
import com.dterz.service.TransactionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transactions/")
@CrossOrigin
public class TransactionsController {

    Logger logger = LoggerFactory.getLogger(TransactionsController.class);

    private final TransactionsService transactionsService;

    @Autowired
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

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


    @GetMapping("getAllFiltered")
    public List<TransactionDTO> getAllFiltered(@RequestParam(name = "sort", defaultValue = "") String sort,
                                               @RequestParam(name = "page", defaultValue = "1") String page,
                                               @RequestParam(name = "size", defaultValue = "5") String size,
                                               @RequestParam(name = "description", defaultValue = "") String description,
                                               @RequestParam(name = "type", defaultValue = "") String type,
                                               @RequestParam(name = "userID", defaultValue = "") String userID) {
        return transactionsService.getAllFiltered(sort, page, size, description, type, userID);
    }

    @GetMapping("getAllForAccount")
    public List<TransactionDTO> getAllForAccount(@RequestParam(name = "sort", defaultValue = "") String sort,
                                                 @RequestParam(name = "page", defaultValue = "1") String page,
                                                 @RequestParam(name = "size", defaultValue = "5") String size,
                                                 @RequestParam(name = "accountId", defaultValue = "") String accountId) {
        return transactionsService.getAllForAccount(sort, page, size, accountId);
    }
}
