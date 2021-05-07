package com.dterz.service;

import com.dterz.dtos.AccountDTO;
import com.dterz.mappers.AccountMapper;
import com.dterz.model.Account;
import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import com.dterz.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper mapper;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.mapper = mapper;
    }

    public List<AccountDTO> getAllFiltered(String userID) {
        List<Account> acountList = accountRepository.findByUsers_Id(1);
        acountList.forEach(this::calcBalance);
        return mapper.entityListToDTOList(acountList);
    }

    private void calcBalance(Account account) {
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction transaction : account.getTransactions()) {
            if (transaction.getType().equals(TransanctionType.INCOME)) {
                balance = balance.add(transaction.getAmount());
            } else {
                balance = balance.subtract(transaction.getAmount());
            }
        }
        account.setCalculatedBalance(balance);
    }

    public AccountDTO getAccountById(long accountId) {
        Optional<Account> account = accountRepository.findById(accountId);
        calcBalance(account.get());
        return mapper.entityToDto(account.get());
    }
}
