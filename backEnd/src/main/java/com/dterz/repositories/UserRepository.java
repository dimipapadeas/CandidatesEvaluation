package com.dterz.repositories;

import com.dterz.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

    User findByUserName(String uName);
}
