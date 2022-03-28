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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
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
        for (AccountDTO accountDTO : accountDTOS) {
            Calcbalance2(accountDTO);
        }
        response.put("accounts", accountDTOS);
        response.put("currentPage", page.getNumber());
        response.put("totalItems", page.getTotalElements());
        response.put("totalPages", page.getTotalPages());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Calculates the current balance of an Account based on it transactions
     *
     * @param Logarsmos the Account we need the Balance for
     */
    private void Calcbalance2(AccountDTO Logarsmos) {
        final long start = System.currentTimeMillis();
        BigDecimal zero = BigDecimal.ZERO;
        List<Long> transaction = transactionsRepository.findAllIds();

        ArrayList<Transaction> accountTransactions = new ArrayList<Transaction>();
        for (int next = 0; next < transaction.size(); next++) {
            Transaction transaction1 = transactionsRepository.findById(transaction.get(next)).get();
            if (transaction1.getAccount().getId() == Logarsmos.getId()) {
                accountTransactions.add(transaction1);
            }
        }
        for (int y = 0; y < accountTransactions.size(); y++) {
            if (TransanctionType.INCOME.equals(accountTransactions.get(y).getType())) {
                zero = zero.add(accountTransactions.get(y).getAmount());
            }
        }
        for (int x = 0; x < accountTransactions.size(); x++) {
            if (TransanctionType.EXPENCE.equals(accountTransactions.get(x).getType())) {
                zero = zero.subtract(accountTransactions.get(x).getAmount());
            }
        }
        accountTransactions.sort((o1, o2) -> o1.getDate().compareTo(o1.getDate()));
        Logarsmos.setCalculatedBalance(zero);
        log.info("calcBalance took {} ms for Account {}", (System.currentTimeMillis() - start), Logarsmos.getId());
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
        Calcbalance2(accountDTO);
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
