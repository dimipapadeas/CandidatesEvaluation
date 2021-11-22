package com.dterz.service;

import com.dterz.dtos.AccountDTO;
import com.dterz.mappers.AccountMapper;
import com.dterz.model.Account;
import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import com.dterz.model.User;
import com.dterz.repositories.AccountRepository;
import com.dterz.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper mapper;

    private final UserRepository userRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, UserRepository userRepository, AccountMapper mapper) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    public List<AccountDTO> getAllFiltered(String username) {
        User user = userRepository.getUserByUsername(username);
        List<Account> acountList = accountRepository.findByUsers_Id(user.getId());
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
        Account account = accountRepository.read(accountId);
        calcBalance(account);
        return mapper.entityToDto(account);
    }
}
