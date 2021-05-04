package com.dterz.repositories;

import com.dterz.model.JWTToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthRepository extends JpaRepository<JWTToken, Long> {

    @Query("SELECT token FROM JWTToken WHERE userName = ?1")
    String getTokenByUsername(String userName);

    @Query(" FROM JWTToken WHERE userName = ?1")
    JWTToken getJWTByUsername(String userName);

}
