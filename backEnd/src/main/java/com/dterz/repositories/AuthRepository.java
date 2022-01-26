package com.dterz.repositories;

import com.dterz.model.JWTToken;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class AuthRepository extends GenericRepository<JWTToken> {
    
}
