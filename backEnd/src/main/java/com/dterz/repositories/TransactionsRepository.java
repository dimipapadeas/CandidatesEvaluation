package com.dterz.repositories;

import com.dterz.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends GenericRepository<Transaction> {

    Page<Transaction> findByUserId(long userId, Pageable pageRequest);

    Page<Transaction> findByAccountId(long accountId, Pageable pageRequest);

    Page<Transaction> findByUserIdAndDescriptionContaining(Long userId, String description, Pageable pageRequest);
}
