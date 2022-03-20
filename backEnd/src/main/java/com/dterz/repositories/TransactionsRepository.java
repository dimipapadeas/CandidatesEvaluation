package com.dterz.repositories;

import com.dterz.model.Transaction;
import com.dterz.model.TransanctionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends GenericRepository<Transaction> {

    Page<Transaction> findByUserId(long userId, Pageable pageRequest);

    Page<Transaction> findByAccountId(long accountId, Pageable pageRequest);

    Page<Transaction> findByUserIdAndDescriptionContaining(Long userId, String description, Pageable pageRequest);

    List<Transaction> findByTypeAndAccount_Id(TransanctionType type, long accountId);
}

