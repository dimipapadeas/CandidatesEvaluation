package com.dterz.repositories;

import com.dterz.model.QTransaction;
import com.dterz.model.Transaction;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TransactionsRepository extends GenericRepository<Transaction> {

    public List<Transaction> getAllByUserId(long userId) {
        QTransaction transaction = QTransaction.transaction;
        final JPAQuery<Transaction> query = new JPAQuery<>(entityManager);
        return query.select(transaction).from(transaction).where(transaction.user.id.eq(userId)).fetch();
    }

    public List<Transaction> getAllByAccountId(long accountId) {
        QTransaction transaction = QTransaction.transaction;
        final JPAQuery<Transaction> query = new JPAQuery<>(entityManager);
        return query.select(transaction).from(transaction).where(transaction.account.id.eq(accountId)).fetch();
    }
}
