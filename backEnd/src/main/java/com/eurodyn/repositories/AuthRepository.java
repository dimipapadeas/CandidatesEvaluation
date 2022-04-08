package com.eurodyn.repositories;

import com.eurodyn.model.JWTToken;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends GenericRepository<JWTToken> {
}
