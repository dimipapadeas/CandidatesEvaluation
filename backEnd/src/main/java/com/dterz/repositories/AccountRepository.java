package com.dterz.repositories;

import com.dterz.model.Account;
import com.dterz.model.QAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends GenericRepository<Account> {

    default Page<Account> findByUserId(long userId, Pageable pageRequest) {
        QAccount account = QAccount.account;
        return findAll(account.users.any().id.eq(userId), pageRequest);
    }

    Account findByDescription(String description);
}
