package com.dterz.repositories;

import com.dterz.model.JWTToken;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends GenericRepository<JWTToken> {
}
