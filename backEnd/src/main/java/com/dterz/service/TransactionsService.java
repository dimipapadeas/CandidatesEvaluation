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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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

    public List<TransactionDTO> getAllFiltered(String sort, String page, String size, String description, String type, String username) {
        User user = userRepository.findByUsername(username);
        List<Transaction> allByUserId = transactionsRepository.findByUserId(user.getId());
        return mapper.entityListToDTOList(allByUserId);
    }

    public List<TransactionDTO> getAllForAccount(String sort, String page, String size, String accountId) {
        List<Transaction> allByAccountId = transactionsRepository.findByAccountId(Long.parseLong(accountId));
        return mapper.entityListToDTOList(allByAccountId);
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

        User user = userRepository.findByUsername(dto.getUserName());
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
