package com.dterz.repositories;

import com.dterz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User WHERE uName = ?1")
    User getUserByUsername(String uName);
}
