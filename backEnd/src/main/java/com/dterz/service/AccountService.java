package com.dterz.service;

import com.dterz.dtos.AccountDTO;
import com.dterz.mappers.AccountMapper;
import com.dterz.model.Account;
import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import com.dterz.model.User;
import com.dterz.repositories.AccountRepository;
import com.dterz.repositories.UserRepository;
import java.util.Optional;
import liquibase.repackaged.org.apache.commons.collections4.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;
    private final UserRepository userRepository;

    public List<AccountDTO> getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        List<Account> acountList = null;
        if (user != null) {
            acountList = accountRepository.findByUsers_Id(user.getId());
        } else {
            acountList = accountRepository.findAll();
        }
        acountList.forEach(this::calcBalance);
        return mapper.entityListToDTOList(acountList);
    }

    private void calcBalance(Account account) {
        BigDecimal balance = BigDecimal.ZERO;
        if (CollectionUtils.isNotEmpty(account.getTransactions())) {
            for (Transaction transaction : account.getTransactions()) {
                if (transaction.getType().equals(TransanctionType.INCOME)) {
                    balance = balance.add(transaction.getAmount());
                } else {
                    balance = balance.subtract(transaction.getAmount());
                }
            }
        }
        account.setCalculatedBalance(balance);
    }

    public AccountDTO getAccountById(long accountId) {
        Account account = accountRepository.findById(accountId).get();
        calcBalance(account);
        return mapper.entityToDto(account);
    }

    public AccountDTO draftAccount() {
        Account account = new Account();
        return mapper.entityToDto(account);
    }

    public AccountDTO updateAccount(AccountDTO accountDTO) {

        Optional<Account> accountOpt = accountRepository.findById(accountDTO.getId());
        Account account;
        if (accountOpt.isPresent()) {
            account = accountOpt.get();
            mapper.dtoToEntity(accountDTO, account);
        } else {
            account = mapper.dtoToEntity(accountDTO);
        }
        accountRepository.save(account);
        return mapper.entityToDto(account);
    }
}
