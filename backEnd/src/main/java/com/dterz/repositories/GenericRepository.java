package com.dterz.repositories;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long>,
    QuerydslPredicateExecutor<T> {

    default void delete(Long id) {
        findById(id).ifPresent(this::delete);
    }

    default List<T> iterableToList(Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
            .collect(Collectors.toList());
    }
}
