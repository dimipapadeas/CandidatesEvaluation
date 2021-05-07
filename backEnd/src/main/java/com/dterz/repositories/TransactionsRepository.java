package com.dterz.repositories;

import com.dterz.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query("FROM Transaction WHERE user.id = ?1")
    List<Transaction> getAllByUserId(long userId);

    @Query("FROM Transaction WHERE account.id = ?1")
    List<Transaction> getAllByAccountId(long accountId);
}
