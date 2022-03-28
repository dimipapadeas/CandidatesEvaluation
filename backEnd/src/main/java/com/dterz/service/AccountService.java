package com.dterz.service;

import com.dterz.dtos.AccountDTO;
import com.dterz.mappers.AccountMapper;
import com.dterz.model.Account;
import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import com.dterz.model.User;
import com.dterz.repositories.AccountRepository;
import com.dterz.repositories.TransactionsRepository;
import com.dterz.repositories.UserRepository;
import liquibase.repackaged.org.apache.commons.collections4.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper mapper;
    private final UserRepository userRepository;
    private final TransactionsRepository transactionsRepository;

    /**
     * Gets all Accounts currently in the System.
     * The parameter is optional and if provided the accounts get filtered by User
     *
     * @param pageRequest number of active page and number of items per page
     * @param username    the username
     * @return ResponseEntity<Map < String, Object>>
     */
    public ResponseEntity<Map<String, Object>> getAll(PageRequest pageRequest, String username) {
        User user = userRepository.findByUserName(username);
        Page<Account> page;
        if (user != null) {
            page = accountRepository.findByUserId(user.getId(), pageRequest);
        } else {
            page = accountRepository.findAll(pageRequest);
        }
        List<Account> acountList = page.getContent();
        Map<String, Object> response = new HashMap<>();
        List<AccountDTO> accountDTOS = mapper.entityListToDTOList(acountList);
        accountDTOS.forEach(this::calcBalance);
        response.put("accounts", accountDTOS);
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Calculates the current balance of an Account based on it transactions
     *
     * @param account the Account we need the Balance for
     */
    private void calcBalance(AccountDTO account) {
        BigDecimal balance = BigDecimal.ZERO;
        List<Transaction> income = transactionsRepository.findByTypeAndAccount_Id(TransanctionType.INCOME, account.getId());
        List<Transaction> expences = transactionsRepository.findByTypeAndAccount_Id(TransanctionType.EXPENCE, account.getId());
        if (CollectionUtils.isNotEmpty(income)) {
            balance = income.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        if (CollectionUtils.isNotEmpty(expences)) {
            BigDecimal expen = expences.stream().map(Transaction::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
            balance = balance.subtract(expen);
        }
        account.setCalculatedBalance(balance);
    }

    /**
     * Gets an Acount by its id
     *
     * @param accountId the id of the Account to get
     * @return AccountDTO
     */
    public AccountDTO getAccountById(long accountId) {
        Account account = accountRepository.findById(accountId).get();
        AccountDTO accountDTO = mapper.entityToDto(account);
        calcBalance(accountDTO);
        return accountDTO;
    }

    /**
     * Creates and returns a new empty Account Object
     *
     * @return AccountDTO
     */
    public AccountDTO draftAccount() {
        Account account = new Account();
        return mapper.entityToDto(account);
    }

    /**
     * Updates the Account with the data from the Front end
     *
     * @param accountDTO the updated Account
     * @return AccountDTO
     */
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
