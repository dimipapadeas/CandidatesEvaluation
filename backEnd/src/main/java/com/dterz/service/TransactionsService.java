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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionsService {

    private final TransactionsRepository transactionsRepository;

    private final AccountRepository accountRepository;

    private final UserRepository userRepository;

    private final TransactionMapper mapper;

    @Autowired
    public TransactionsService(TransactionsRepository transactionsRepository, AccountRepository accountRepository, UserRepository userRepository, TransactionMapper mapper) {
        this.transactionsRepository = transactionsRepository;
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<TransactionDTO> getAllFiltered(String sort, String page, String size, String description, String type, String userID) {
        List<Transaction> allByUserId = transactionsRepository.getAllByUserId(1);
        return mapper.entityListToDTOList(allByUserId);
    }

    public List<TransactionDTO> getAllForAccount(String sort, String page, String size, String accountId) {
        List<Transaction> allByAccountId = transactionsRepository.getAllByAccountId(Long.parseLong(accountId));
        return mapper.entityListToDTOList(allByAccountId);
    }

    public void deleteTransaction(long transactionId) {
        transactionsRepository.deleteById(transactionId);
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
        Transaction transaction;
        Optional<Transaction> trans = transactionsRepository.findById(dto.getId());
        User user = userRepository.getUserByUsername(dto.getUserName());
        Account account = accountRepository.getUserByUsername(dto.getAccountName());
        if (trans.isPresent()) {
            transaction = trans.get();
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
