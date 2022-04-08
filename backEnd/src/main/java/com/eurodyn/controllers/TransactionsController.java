package com.eurodyn.controllers;

import com.eurodyn.dtos.TransactionDTO;
import com.eurodyn.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/transactions/")
@CrossOrigin
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionsService transactionsService;

    /**
     * Deletes a Transaction from the Database
     *
     * @param transactionId the Id of the Transaction to delete
     */
    @DeleteMapping("delete/{transactionId}")
    public void delete(@PathVariable("transactionId") long transactionId) {
        transactionsService.deleteTransaction(transactionId);
    }

    /**
     * Create and returns a new empty Transaction Object for the Account specified
     *
     * @param accountId The account that requested a new Transaction
     * @return TransactionDTO
     */
    @GetMapping("createDraftTransaction/{accountId}")
    public TransactionDTO createDraftTransaction(@PathVariable("accountId") String accountId) {
        return transactionsService.createDraftTransaction(accountId);
    }

    /**
     * Updates the Transaction with the data from the Front end
     *
     * @param transaction the updated Transaction
     * @return TransactionDTO
     */
    @PutMapping("update")
    public TransactionDTO update(@RequestBody TransactionDTO transaction) {
        return transactionsService.updateTransaction(transaction);
    }

    /**
     * Gets a Transaction by its id
     *
     * @param transactionId the id of the Transaction requested
     * @return TransactionDTO
     */
    @GetMapping("getTransactionIdById/{transactionId}")
    public TransactionDTO getTransactionIdById(@PathVariable("transactionId") long transactionId) {
        return transactionsService.getTransactionIdById(transactionId);
    }

    /**
     * Gets a List of Transaction for a given User according to some sort and filter criteria
     *
     * @param sort        the attribute the list will be sorted by
     * @param direction   the sort direction selected
     * @param page        the active page requested
     * @param size        the number of item requested per page
     * @param description the input for filtering the Transactions by description
     * @param type        unused parameter
     * @param userID      the name of the User to filter the transactions by
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping("getAllForUser")
    public ResponseEntity<Map<String, Object>> getAllFiltered(@RequestParam(name = "sort", defaultValue = "date") String sort,
                                                              @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                              @RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "5") int size,
                                                              @RequestParam(name = "description", defaultValue = "") String description,
                                                              @RequestParam(name = "type", defaultValue = "") String type,
                                                              @RequestParam(name = "userID", defaultValue = "") String userID) {
        PageRequest pageRequest = PageRequest.of(page, size, direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        Map<String, Object> response = transactionsService.getAllForUser(pageRequest, userID, description);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Gets a List of Transaction for a given Account according to some sort and filter criteria
     *
     * @param sort      the attribute the list will be sorted by
     * @param direction the sort direction selected
     * @param page      the active page requested
     * @param size      the number of item requested per page
     * @param accountId the id of the Account to filter the transactions by
     * @return ResponseEntity<Map < String, Object>>
     */
    @GetMapping("getAllForAccount")
    public ResponseEntity<Map<String, Object>> getAllForAccount(@RequestParam(name = "sort", defaultValue = "date") String sort,
                                                                @RequestParam(name = "direction", defaultValue = "asc") String direction,
                                                                @RequestParam(name = "page", defaultValue = "0") int page,
                                                                @RequestParam(name = "size", defaultValue = "5") int size,
                                                                @RequestParam(name = "accountId", defaultValue = "") String accountId) {
        PageRequest pageRequest = PageRequest.of(page, size, direction.equals("asc") ? Sort.by(sort).ascending() : Sort.by(sort).descending());
        Map<String, Object> response = transactionsService.getAllForAccount(pageRequest, accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
