package com.eurodyn.repositories;

import com.eurodyn.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User> {

    /**
     * Gets an User by its username
     *
     * @param uName thename to find matching User
     * @return User
     */
    User findByUserName(String uName);
}
