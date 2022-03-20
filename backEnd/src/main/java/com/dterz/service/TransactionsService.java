package com.dterz.service;

import com.dterz.dtos.TransactionDTO;
import com.dterz.mappers.TransactionMapper;
import com.dterz.model.Account;
import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import com.dterz.model.User;
import com.dterz.repositories.AccountRepository;
import com.dterz.repositories.TransactionsRepository;
import com.dterz.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Map<String, Object>> getAllForUser(PageRequest pageRequest, String username, String description) {
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
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Map<String, Object>> getAllForAccount(PageRequest pageRequest, String accountId) {
        Page<Transaction> page = transactionsRepository.findByAccountId(Long.parseLong(accountId), pageRequest);
        List<Transaction> allByAccount = page.getContent();
        Map<String, Object> response = new HashMap<>();
        response.put("transactions", mapper.entityListToDTOList(allByAccount));
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public void deleteTransaction(long transactionId) {
        transactionsRepository.delete(transactionId);
    }

    public TransactionDTO createDraftTransaction(String accountId) {
        Account account = accountRepository.findById(Long.valueOf(accountId)).get();
        Transaction draft = new Transaction();
        draft.setAccount(account);
        draft.setDate(new Date());
        draft.setType(TransanctionType.EXPENCE);
        return mapper.entityToDto(draft);
    }

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

    public TransactionDTO getTransactionIdById(long transactionId) {
        Transaction transaction = transactionsRepository.findById(transactionId).get();
        return mapper.entityToDto(transaction);
    }
}
