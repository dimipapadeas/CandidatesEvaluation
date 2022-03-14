package com.dterz.repositories;

import com.dterz.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends GenericRepository<Transaction> {

    List<Transaction> findByUserId(long userId);

    List<Transaction> findByAccountId(long accountId);
}
