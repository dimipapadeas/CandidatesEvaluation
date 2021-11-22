package com.dterz.repositories;

import com.dterz.model.Account;
import com.dterz.model.QAccount;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountRepository extends GenericRepository<Account> {

    public List<Account> findByUsers_Id(long userId) {
        QAccount account = QAccount.account;
        final JPAQuery<Account> query = new JPAQuery<>(entityManager);
        return query.select(account).from(account).where(account.users.any().id.eq(userId)).fetch();
    }

    public Account getUserByUsername(String description) {
        QAccount account = QAccount.account;
        final JPAQuery<Account> query = new JPAQuery<>(entityManager);
        return query.select(account).from(account).where(account.description.eq(description)).fetchFirst();
    }
}
