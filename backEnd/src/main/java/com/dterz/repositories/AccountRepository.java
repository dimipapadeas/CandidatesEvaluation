package com.dterz.repositories;

import com.dterz.model.Account;
import com.dterz.model.QAccount;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends GenericRepository<Account> {

    default List<Account> findByUsers_Id(long userId) {
        QAccount account = QAccount.account;
        return iterableToList(findAll(account.users.any().id.eq(userId)));
    }

    Account findByDescription(String description);
}
