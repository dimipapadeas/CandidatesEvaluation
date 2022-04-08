package com.eurodyn.service;

import com.eurodyn.dtos.TransactionDTO;
import com.eurodyn.mappers.TransactionMapper;
import com.eurodyn.model.Account;
import com.eurodyn.model.Transaction;
import com.eurodyn.model.TransanctionType;
import com.eurodyn.model.User;
import com.eurodyn.repositories.AccountRepository;
import com.eurodyn.repositories.TransactionsRepository;
import com.eurodyn.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionMapper mapper;

    /**
     * Gets a List of Transaction for a given User according to some sort and filter criteria
     *
     * @param pageRequest sort and filter criteria for the requested transactions
     * @param username    the name of the User to filter the transactions by
     * @param description the input for filtering the Transactions by description
     * @return ResponseEntity<Map < String, Object>>
     */
    public Map<String, Object> getAllForUser(PageRequest pageRequest, String username, String description) {
        User user = userRepository.findByUserName(username);
        Page<Transaction> page;
        if (StringUtils.isNotEmpty(description)) {
            page = transactionsRepository.findByUserIdAndDescriptionContaining(user.getId(), description, pageRequest);
        } else {
            page = transactionsRepository.findByUserId(user.getId(), pageRequest);
        }
        List<Transaction> transactions = page.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("transactions", mapper.entityListToDTOList(transactions));
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    /**
     * Gets a List of Transaction for a given Account according to some sort and filter criteria
     *
     * @param pageRequest sort and filter criteria for the requested transactions
     * @param accountId   the id of the User to filter the transactions by
     * @return ResponseEntity<Map < String, Object>>
     */
    public Map<String, Object> getAllForAccount(PageRequest pageRequest, String accountId) {
        Page<Transaction> page = transactionsRepository.findByAccountId(Long.parseLong(accountId), pageRequest);
        List<Transaction> allByAccount = page.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("transactions", mapper.entityListToDTOList(allByAccount));
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return response;
    }

    /**
     * Deletes a Transaction from the Database
     *
     * @param transactionId the Id of the Transaction to delete
     */
    public void deleteTransaction(long transactionId) {
        transactionsRepository.delete(transactionId);
    }

    /**
     * Create and returns a new empty Transaction Object for the Account specified
     *
     * @param accountId The account that requested a new Transaction
     * @return TransactionDTO
     */
    public TransactionDTO createDraftTransaction(String accountId) {
        Account account = accountRepository.findById(Long.valueOf(accountId)).orElse(null);
        Transaction draft = new Transaction();
        draft.setAccount(account);
        draft.setDate(new Date());
        draft.setType(TransanctionType.EXPENCE);
        return mapper.entityToDto(draft);
    }

    /**
     * Updates the Transaction with the data from the Front end
     *
     * @param dto the updated Transaction
     * @return TransactionDTO
     */
    public TransactionDTO updateTransaction(TransactionDTO dto) {
        Optional<Transaction> transactionOpt = transactionsRepository.findById(dto.getId());
        Transaction transaction;

        User user = userRepository.findByUserName(dto.getUserName());
        Account account = accountRepository.findByDescription(dto.getAccountName());
        if (transactionOpt.isPresent()) {
            transaction = transactionOpt.get();
            mapper.dtoToEntity(dto, transaction);
        } else {
            transaction = mapper.dtoToEntity(dto);
        }
        transaction.setUser(user);
        transaction.setAccount(account);
        transactionsRepository.save(transaction);
        return mapper.entityToDto(transaction);
    }

    /**
     * Gets a Transaction by its id
     *
     * @param transactionId the id of the Transaction requested
     * @return TransactionDTO
     */
    public TransactionDTO getTransactionIdById(long transactionId) {
        Transaction transaction = transactionsRepository.findById(transactionId).orElse(null);
        return mapper.entityToDto(transaction);
    }
}
